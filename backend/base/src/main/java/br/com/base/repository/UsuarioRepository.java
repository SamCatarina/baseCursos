package br.com.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.base.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
