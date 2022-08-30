package com.generation.meublogpessoal.meublogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.meublogpessoal.meublogpessoal.model.Postagem;

@Repository //classe de repositório
public interface PostagemRepository extends JpaRepository<Postagem, Long> { //JpaRepository<Titulo da entidade , tipo do ID (long)> {
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	//find all by titulo: buscar todos pelo Título; containing = like (tudo que contém Título); ignore case: ignorar se é maiúsculo ou minúsculo
	
}