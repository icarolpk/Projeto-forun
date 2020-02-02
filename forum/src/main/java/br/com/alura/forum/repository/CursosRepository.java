package br.com.alura.forum.repository;

import br.com.alura.forum.controller.dto.cursos.CursosDto;
import br.com.alura.forum.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface CursosRepository extends JpaRepository<Curso,Long> {

    Curso findByNome(String nome);
}

