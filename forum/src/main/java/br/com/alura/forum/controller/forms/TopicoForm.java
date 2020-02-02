package br.com.alura.forum.controller.forms;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursosRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoForm {

    @NotNull @NotEmpty @Length(min=5)     // Usando anotações do 'BEANVALIDATION' para verificar se minhas requisições de post são,NULAS, VAZIAS e TAMANHO MINIMO.
    private String titulo;

    @NotNull @NotEmpty @Length(min=10)
    private String mensagem;

    @NotNull @NotEmpty @Length(min=4)
    private String nomeCurso;


    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public Topico converter(CursosRepository cursosRepository) { //Como nesse caso eu precsava carregar o curso do banco de dados e no construtor eu so recebia o nome do curso, eu coloquei como parametro uma referencia ao repository para acessar o dado do objeto
        Curso curso = cursosRepository.findByNome(nomeCurso);
        return new Topico(titulo,mensagem,curso);
        //Metodo que é responsavel por converter os dados vindos do cliente para popular o BD da aplicação.
    }
}
