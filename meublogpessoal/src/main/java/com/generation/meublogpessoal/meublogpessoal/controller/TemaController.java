package com.generation.meublogpessoal.meublogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.meublogpessoal.meublogpessoal.model.Postagem;
import com.generation.meublogpessoal.meublogpessoal.model.Tema;
import com.generation.meublogpessoal.meublogpessoal.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {

@Autowired
private TemaRepository repository;

@GetMapping
public ResponseEntity<List<Tema>> getAll(){
	return ResponseEntity.ok(repository.findAll());
}

@GetMapping("/{id}")
public ResponseEntity<Tema> getById(@PathVariable Long id){
	return repository.findById(id)
			.map(resp -> ResponseEntity.ok (resp))
.orElse(ResponseEntity.notFound().build());
}

@GetMapping("/descricao/{descricao}")
public ResponseEntity<List<Tema>> getByName(@PathVariable String descricao){
	return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
}

@PostMapping
public ResponseEntity<Tema> post (@RequestBody Tema tema){
	return ResponseEntity.status(HttpStatus.CREATED)
			.body (repository.save(tema));
}

@PutMapping//post e put:usa sempre o RequestBody. Procuro no Insomnia no modo Json
public ResponseEntity<Tema> atualizarPostagem(@Valid @RequestBody Tema tema){//requestbody: vai chamar o "corpo" da minha classe Postagem q ja existe. Postagem postagem (Objeto + objeto q eu instanciei- sem precisar de Postagem = new postagem). @Valid: valida o corpo da requisição antes de mandar no banco de dados(se ta vazia ou nao)
    return repository.findById(tema.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.OK)//aqui nao tem List porque ele insere UMA COISA de cada vez, o mesmo do post. 
            .body(repository.save(tema)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}


@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/{id}") //posts/o numero do ID e delete
public void delete (@PathVariable Long id) {
	Optional <Tema> tema = repository.findById(id); // opção: se existir, apaga a postagem. se não existir, não faz nada
	if (tema.isEmpty())
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		repository.deleteById(id);
	
}
}
