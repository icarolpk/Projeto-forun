package br.com.alura.forum.controller.forms;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursosRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CursoForm {  //Classe que recebera os dados que seram recebidos do cliente.

    @NotNull @NotEmpty @Length(min=5)
    private String nome;
    @NotNull @NotEmpty @Length(min=2)
    private String categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Curso converter(CursosRepository cursosRepository) {
        Curso curso = cursosRepository.findByNome(nome);
        return new Curso(nome,categoria);
    }
}