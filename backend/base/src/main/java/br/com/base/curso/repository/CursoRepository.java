package br.com.base.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.base.curso.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
