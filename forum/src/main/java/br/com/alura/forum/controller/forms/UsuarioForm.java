package br.com.alura.forum.controller.forms;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UsuarioForm {

    @NotNull @NotEmpty @Length(min=5)
    private String nome;

    @NotNull @NotEmpty @Length(min=5)
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario converter(UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.findByNome(nome);
        return new Usuario(nome);

    }
}
