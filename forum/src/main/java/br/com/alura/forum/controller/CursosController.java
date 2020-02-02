package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.cursos.CursosDto;
import br.com.alura.forum.controller.forms.CursoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping
    public List<CursosDto> listar(String nome) {
            List<Curso> curso = cursosRepository.findAll();
            return CursosDto.converter(curso);
    }

    @PostMapping
    public ResponseEntity<CursosDto> cadastrar(@RequestBody @Valid CursoForm form, UriComponentsBuilder uriBuilder){
        Curso curso = form.converter(cursosRepository);
        cursosRepository.save(curso);

        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new CursosDto(curso));
    }

}
