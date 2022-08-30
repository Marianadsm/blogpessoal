package com.generation.meublogpessoal.meublogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.meublogpessoal.meublogpessoal.model.Postagem;
import com.generation.meublogpessoal.meublogpessoal.repository.PostagemRepository;

@RestController //para indicar ao spring que é uma classe de Controller
@RequestMapping("/posts") //para buscar esta classe
@CrossOrigin("*") //Diz que pode ser de qualquer origem e a API irá aceitar
public class PostagemController {
	
	@Autowired //para garantir que tds os serviços de Repository seja acessado pela minha classe controller
	private PostagemRepository repository;
	
	@GetMapping //sempre que vier uma requisição externa atraves de "postagens", irá aparecer o método abaixo
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll()); //findAll metodo que retorna uma lista de postagens/todos os dados da postagem
	}
}