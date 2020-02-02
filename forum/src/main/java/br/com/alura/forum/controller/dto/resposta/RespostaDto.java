package br.com.alura.forum.controller.dto.resposta;

import br.com.alura.forum.modelo.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {

    private Long id;
    private String mensage;
    private LocalDateTime dataDeCriacao;
    private String nomeAutor;

    public RespostaDto(Resposta resposta){
        this.id = resposta.getId();
        this.mensage = resposta.getMensagem();
        this.dataDeCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensage() {
        return mensage;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
