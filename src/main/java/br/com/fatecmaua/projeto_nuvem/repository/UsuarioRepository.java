package br.com.fatecmaua.projeto_nuvem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatecmaua.projeto_nuvem.model.Usuario;

public interface UsuarioRepository extends 
JpaRepository<Usuario,Long> {
	
	Optional<Usuario> findByEmail(String email);

}
