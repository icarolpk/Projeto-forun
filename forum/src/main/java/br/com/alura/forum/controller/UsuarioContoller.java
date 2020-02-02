package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.topicos.TopicosDto;
import br.com.alura.forum.controller.dto.usuario.DetalhesUsuarioDto;
import br.com.alura.forum.controller.dto.usuario.UsuarioDto;
import br.com.alura.forum.controller.forms.UsuarioForm;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("usuarios")

public class UsuarioContoller {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDto> listar(String nome) {
            List<Usuario> usuario = usuarioRepository.findAll();
            return UsuarioDto.converter(usuario);
    }
    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder){
        Usuario usuario = form.converter(usuarioRepository);
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
    @GetMapping("/{id}")
    public DetalhesUsuarioDto detalhar(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getOne(id);
        return new DetalhesUsuarioDto(usuario);
    }
}
