package br.com.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.base.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
