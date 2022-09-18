package com.generation.meublogpessoal.meublogpessoal.service;

// ********Service: VERIFICAÇÕES/validações p/ cadastrar um usuario antes de ir pro controller/banco de dados.
import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.meublogpessoal.meublogpessoal.model.Usuario;
import com.generation.meublogpessoal.meublogpessoal.model.UsuarioLogin;
import com.generation.meublogpessoal.meublogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// ***CADASTRAR USUARIO
	// verifica se o usuário ja está cadastrado, criptografa a senha e manda o
	// objeto de usuario para o banco de dados
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty(); // retorna um erro (se ja existe esse usuario, a pessoa não consegue cadastrar
										// com ele)

		/* else */ usuario.setSenha(criptografarSenha(usuario.getSenha())); // se nao existe, ele vai criptografar a
																			// senha e mandar para o banco de dados

		return Optional.of(usuarioRepository.save(usuario)); // manda o objeto do banco de dados com a senha já
																// criptografada

	}

	// ***ATUALIZAR USUARIO

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getId()).isPresent()) { //verifica se o Id existe 

			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());// olha se minha
																									// atualização já
																									// existe (se ja
																									// existe um email q
																									// to tentando mudar
																									// pro meu usuario)

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != usuario.getId()))//se um dos dois for false, ele vai dar um BadRequest
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			/*if else*/ usuario.setSenha(criptografarSenha(usuario.getSenha()));// aqui precisa criptografar a senha tb, pois sempre
																	// q a pessoa muda a senha, a criptografia tb muda

			return Optional.ofNullable(usuarioRepository.save(usuario)); // ofnullable: valida se oscampos que to
																			// atualizando nao ficaram nulos (usuario ou
																			// senha)

		}

		/*else*/ return Optional.empty();// se não existe o usuario (linha 40), ele cai aqui. fica vazio

	}

	// ***LOGIN

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {

			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				/*else*/ return usuarioLogin;

			}
		}

	/*else*/	return Optional.empty();

	}

//FUNCAO DE CRIPTOGRAFAR SENHA pra cadastrar e atualizar usuario
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // semelhante ao Scanner() do Java. Pega as letras
																		// do teclado

		return encoder.encode(senha);// usando o método do bcrypt que criptografa a senha digitada e retorna a senha
										// já criptografada(encode é o metodo que criptografa a senha)

	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);//matches descriptografa a senha

	}

	private String gerarBasicToken(String usuario, String senha) { //usuario +senha = token. aqui ele criptografa a senha

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));//us ascii é um padrao de criptografia
		return "Basic " + new String(tokenBase64);

	}

}