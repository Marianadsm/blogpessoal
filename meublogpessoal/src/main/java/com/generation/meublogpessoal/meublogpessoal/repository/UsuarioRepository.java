package com.generation.meublogpessoal.meublogpessoal.repository;
	
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.meublogpessoal.meublogpessoal.model.Usuario;


@Repository
	public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

		public Optional<Usuario> findByUsuario(String usuario); // Optional checa se existe ou não existe o usuário
		
}
