package com.generation.meublogpessoal.meublogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.meublogpessoal.meublogpessoal.model.Postagem;
import com.generation.meublogpessoal.meublogpessoal.repository.PostagemRepository;

//Controller só serve para chamar o método do repository

@RestController //para indicar ao spring que é uma classe de Controller(Controller-ter acesso ao Get, post, put e delete)
//endpoint (RequestMappint) não pode ter letra maiuscula nem espaço dentro dele
@RequestMapping("/posts") //passa o end point, e esse endpoint é o ENDEREÇO pra encontrar e executar a função(para procurar o meu endereço no localhost-insomnia/postman)
@CrossOrigin("*") //Diz que pode ser de qualquer origem e a API irá aceitar
public class PostagemController {
	
	@Autowired //para garantir que tds os serviços de Repository seja acessado pela minha classe Controller. É o Repository que tem a responsabilidade de FAZER as operações Create post put e delete.). 
	//autowired é transferencia de responsabilidade. o controller executa, o repository só traz pra cá os metodos com o autowired(que ele pegou do jpa). cada camada tem uma função
	private PostagemRepository repository;
	
	@GetMapping //sempre que vier uma requisição externa atraves de "posts", irá aparecer o método abaixo(pode ser get, post, put, etc)
	public ResponseEntity<List<Postagem>> GetAll(){ // List : lista de postagens/array. Response: resposta. Postagem: objeto do tipo postagem/ getAll nome da funcao q eu criei (pode ser qqr nome)
		return ResponseEntity.ok(repository.findAll()); //findAll metodo que retorna/BUSCAa lista de postagens/todos os dados da postagem q eu pedi 
		//getmapping: public prepara a resposta, return: executa a resposta do Get
	}
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){ //PathVariable é para passar um valor pela urL, nele recepcionamos este valor
		return repository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		//  /\ procurar a postagem pelo ID
	}
	@GetMapping ("/titulo/{titulo}")//depois da /, o ultimo dado é um atributo.por isso precisa de 2 /.
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	// /\ procurar uma postagem pelo titulo/palavras q contem no titulo
	
	@PostMapping //post e put:usa sempre o RequestBody. Procuro no Insomnia no modo Json
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){ //requestbody: vai chamar o "corpo" da minha classe Postagem q ja existe
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	@PutMapping //post e put:usa sempre o RequestBody. Procuro no Insomnia no modo Json
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){ //requestbody: vai chamar o "corpo" da minha classe Postagem q ja existe
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	@DeleteMapping("/{id}") //posts/o numero do ID e delete
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
		
	}
}
