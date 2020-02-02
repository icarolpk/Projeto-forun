package br.com.alura.forum.controller.dto.topicos;


import br.com.alura.forum.modelo.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//DTO = Data transfer object
//Classe que sera utilizada apenas para repassar valores que eu precisar, nesse caso alguns atributos que eu  queria espor da classe Topicos
public class TopicosDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataDeCriacao;

    public TopicosDto (Topico topico){
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataDeCriacao = topico.getDataCriacao();
    }

    public static List<TopicosDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicosDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
