package com.generation.meublogpessoal.meublogpessoal.security;

//IMPL/IMPLEMENT: diz o que eu quero que implemente, além do PADRAO (e mexer neste padrao tb)
//sobrescreve o userdetails, porem com o que eu quero com a minha aplicaçao
//A Classe UserDetailsImpl implementa a Interface UserDetails, que tem como principal funcionalidade fornecer as informações básicas do usuário para o Spring Security
//(Usuário, Senha, Direitos de acesso e as Restrições da conta).

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.meublogpessoal.meublogpessoal.model.Usuario;


	public class UserDetailsImpl implements UserDetails{ //user details é uma superclasse com métodos padroes de segurança(Password, Username, etc)

	private static final long serialVersionUID =1L;
		
		private String userName;
		private String password;
		
		private List<GrantedAuthority> authorities; //autorização para o usuario que estiver cadastrado e logado
		
		public UserDetailsImpl (Usuario user){ //aqui ele está dizendo que a senha e user não é aleatorio e sim que é pra salvar o que o usuario colocar
			this.userName = user.getUsuario(); //esse Usuario é do meu get e set da model usuario, atibuto Usuario
			this.password = user.getSenha(); //senha da model Usuario, atributo Senha
		}
		
		public UserDetailsImpl (){ } //construtor vazio, q delimita que pra ela se cadastrar e logar ela precisa ter os 2 atributos preenchidos. porém, aceita se a pessoa mandar MAIS informações alem de user, senha (eX: foto tbm)
		
		@Override //aqui diz que todos os usuarios tem a mesma permissao
		public Collection<? extends GrantedAuthority> getAuthorities() {
		
			return authorities;
		}

		@Override 
		public String getPassword() {
		
			return password;
		}

		@Override
		public String getUsername() {
			
			return userName;
		}

		@Override
		public boolean isAccountNonExpired() {
			
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			
			return true;
		}

	    @Override
		public boolean isEnabled() {
			
			return true;
		}

	}

