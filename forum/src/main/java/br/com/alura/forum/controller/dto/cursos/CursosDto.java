package br.com.alura.forum.controller.dto.cursos;

import br.com.alura.forum.controller.dto.topicos.TopicosDto;
import br.com.alura.forum.modelo.Curso;

import java.util.List;
import java.util.stream.Collectors;

public class CursosDto {

    private String nome;

    public CursosDto (Curso curso){
        this.nome = curso.getNome();
    }

    public static List<CursosDto> converter(List<Curso> cursos) {
        return cursos.stream().map(CursosDto::new).collect(Collectors.toList());

    }

    public String getNome() {
        return nome;
    }

}
