package com.generation.meublogpessoal.meublogpessoal.security;

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.http.HttpMethod;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.http.SessionCreationPolicy;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.SecurityFilterChain;

	@Configuration
	@EnableWebSecurity
	public class BasicSecurityConfig {

	    @Bean //faz a instanciação de forma global, ao inves de apenas local
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean // AuthenticationManager: personaliza dizendo que quero que use o usuário e a senha 
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	    
	    @Bean//filterChain define as "permissoes" de ate onde o usuario cadastrado ou logado pode ir e onde ele pode ir sem estar cadastrado ou logadi
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http
	            .sessionManagement() //define que toda requisição da aplicação tem começo, meio e fim. barra por exemplo q tenha um ataque virtual à minha aplicação, para nao ter varias requisições ao mesmo tempo. uma de cada vez (começo,meio e fim)
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and().csrf().disable() // padrao à segurança. bloqueia que faça put ou delete. entao desabilita pra que eu possa fazer o post e delete sim
	            .cors(); //evita bloqueio de acesso de outras portas locais

	        http
	            .authorizeHttpRequests((auth) -> auth
	                .antMatchers("/usuarios/logar").permitAll() // é aberto ao publico sem estar logado (apenas o "Logar"
	                .antMatchers("/usuarios/cadastrar").permitAll() // é aberto ao publico sem estar cadastrado(apenas o "Cadastrar"
	                .antMatchers(HttpMethod.OPTIONS).permitAll() // apos cadastrar e logar, ela pode fazer o que eu permitir aqui(acesso a tudo). eu substituo o OPTIONS para filtrar o que quero deixar disponivel. para deixar todos (post, put, delete, etc), deixa o OPTIONS
	                .anyRequest().authenticated())
	            .httpBasic();

	        return http.build(); //pega as regras que dei (abrir, logar e cadastrar, get put post e delete desde que esteja cadastrado e logado)

	    }

	}
