package br.com.alura.forum.controller.dto.usuario;

import br.com.alura.forum.controller.dto.resposta.RespostaDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DetalhesUsuarioDto {

    private String nome;
    private String email;


    public DetalhesUsuarioDto(Usuario usuario){
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
