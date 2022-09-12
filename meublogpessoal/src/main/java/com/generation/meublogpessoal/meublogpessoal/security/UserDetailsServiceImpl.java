package com.generation.meublogpessoal.meublogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.meublogpessoal.meublogpessoal.model.Usuario;
import com.generation.meublogpessoal.meublogpessoal.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService { 
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException { /// Verifica se tem usuario cadastrado ou logado com o usuario e a senha

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);//verifica se a pessoa existe no meu banco de dados pra cadastro ou login (se ja existe e pode logar ou nao existe ainda e da pra cadastrar)

		if (usuario.isPresent())
			return new UserDetailsImpl(usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}
}


