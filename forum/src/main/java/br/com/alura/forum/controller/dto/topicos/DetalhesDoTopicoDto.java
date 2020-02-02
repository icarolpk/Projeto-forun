package br.com.alura.forum.controller.dto.topicos;

import br.com.alura.forum.controller.dto.resposta.RespostaDto;
import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DetalhesDoTopicoDto {

    private Long id;
    private String ttulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutos;
    private StatusTopico status;
    private List<RespostaDto> respostas;

    public DetalhesDoTopicoDto(Topico topico){
        this.id = topico.getId();
        this.ttulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.nomeAutos = topico.getAutor().getNome();
        this.status = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTtulo() {
        return ttulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutos() {
        return nomeAutos;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
