package br.com.alura.forum.controller.dto.topicos;

import br.com.alura.forum.controller.dto.topicos.TopicosDto;
import br.com.alura.forum.modelo.Topico;

public class MensagemTopicoDto extends TopicosDto {

    private String titulo;
    private String mensagem;

    public MensagemTopicoDto(Topico topico){
        super(topico);
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
