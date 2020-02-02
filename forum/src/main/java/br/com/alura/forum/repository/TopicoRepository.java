package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Long> {


    List<Topico> findByCursoNome(String nome);

    List<Topico> findByCurso_Categoria(String categoria);
    //Usando essa forma de nomenclatura de metodo eu posso filtar a resposta de dados.
    //Repare que nesse caso estou usando o 'findByCursoCategoria' ou seja, traga o resultado da tabela Curso da coluna Categoria(nesse caso é um atributo de relacionamento)

//    @Query("select * from topico t where t.curso.nome = :nome")  // Caso não queira seguir a convenção de nontagem, posso fazer a quety com essa anotação. e embaixo colocar como 'Param' os parametros.
//    List<Topico> carregarPorNomeDoCurso(@Param("nome")String  nome);
}
