package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.topicos.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.forms.AtulizacaoTopicoForm;
import br.com.alura.forum.controller.forms.TopicoForm;
import br.com.alura.forum.controller.dto.topicos.MensagemTopicoDto;
import br.com.alura.forum.controller.dto.topicos.TopicosDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursosRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
// Deixando essa anotação em cima da classe  digo que todos os metodos dessa classe seram chamados com o parametro passado, e nos metodos para diferenciar eu preciso passar o tipo de chamada, POST, GET... etc.
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;  // Essa anotação deve ser feita pois 'TopicoRepository' é uma interface

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping
    public List<TopicosDto> listar(String nome) {
        if (nome == null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicosDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nome);
            return TopicosDto.converter(topicos);
        }
    }

    @RequestMapping("/topicosMensagem")
    public List<TopicosDto> mensgaem(String categoria) {
        if (categoria == null) {
            List<Topico> topicos = topicoRepository.findAll();//Metodo 'findAll' faz uma consulta e traz todos os registros do banco de dados.
            return TopicosDto.converter(topicos);
        } else {
            List<Topico> topicosMensagem = topicoRepository.findByCurso_Categoria(categoria); //Colocando o 'underline' eu digo pro spring que esse dado a ser extraido é do relacionamento das tabelas
            return MensagemTopicoDto.converter(topicosMensagem);
            // Com essa implementação agora estou puxando os dados do banco de dados Usando o 'topicoRepository.findAll()'
        }
    }

    @PostMapping
    @Transactional
    // Usando dentro do paramentro da classe o '@Valid' estamos orientando o spring que dentro da classe passada tambem dentro do paramentro há validações do BEANVALIDATION para serem analizadas e levadas em consideração.
    public ResponseEntity<TopicosDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) { // A anotação 'RequestBody' avisa o spring que esse parametro deve ser pego no corpo da requisição e não na URL(que é quando recebemos um post)
        Topico topico = form.converter(cursosRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicosDto(topico));
        //Função que sera responsavel por devolver codigo mais apropriado para esse cenario de criação de novo recurso.
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto>detalhar(@PathVariable Long id) {   // Sem a anotação 'PathVariable' o spring entenderia esse parametro como parametro de URL, mas nesse caso ele é parte da URL
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }
        return ResponseEntity.notFound().build();
        //Metodo que me retornara nesse caso um topico detalhado, por exemplo, posso passar um "/topicos/3" e ele me trara as informnações desta linha to topico.
    }

    @PutMapping("{id}")
    @Transactional
    // Anotação responsavel por garantir que as informações seram atualzadas no banco de dados, essa anotação commitara as informações para que elas sejam realmente atualizadas.
    public ResponseEntity<TopicosDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtulizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicosDto(topico));
        }
        return ResponseEntity.notFound().build();

        //Metodo responsavel por atualizar os campos necessarios, nesse caso usei a classe 'AtulizacaoTopicoForm' para poder atualizar apenas os titulos e mnensagens respectivamente.

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
