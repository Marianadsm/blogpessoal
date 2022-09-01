package com.generation.meublogpessoal.meublogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.generation.meublogpessoal.meublogpessoal.model.Postagem;

@Repository //classe de repositório. tem que ter em TODO repositorio.diz que há METODOS que irão manipular dados no banco de dados(funcao do repositorio)
//esse extends JpaRepository abaixo, pega a super classe Jpa (que me ajuda a ter os metodos basicos (delete, post, findAll, saveAll,deleteAll,etc etc))
//a partir do momento que eu faço o extends, o meu PostagemRepository já tem todos os métodos. por isso precisa ser interface, pois todos vem do JpaRepository
//Jpa faz a conexão do meu banco de dados com o back end
public interface PostagemRepository extends JpaRepository<Postagem, Long> { //JpaRepository<Titulo da entidade , tipo do ID (long)> {
	public List<Postagem> findAllByTituloContainingIgnoreCase (@Param ("titulo") String titulo);
	//find all by titulo: buscar todos pelo Título; containing = like (tudo que contém Título); ignore case: ignorar se é maiúsculo ou minúsculo
	//findAllBy vem do JpaRepository
	//Param: define a variavel String como um parametro de consulta.
	//o mesmo de, no msq: SELECT*FROM tb_postagens WHERE titulo like "%titulo%";
}