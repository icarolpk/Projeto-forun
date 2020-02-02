package br.com.alura.forum.controller.dto.usuario;

import br.com.alura.forum.controller.dto.cursos.CursosDto;
import br.com.alura.forum.modelo.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {

    private String nome;
    private String email;

    public UsuarioDto(Usuario usuario){
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuario) {
        return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
