package com.generation.meublogpessoal.meublogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//os @ funcionam pro dado logo abaixo deles. eles são exclusividade do spring

@Entity // TODO model deve ter uma @entity. ele indica que essa classe será uma tabela. ENTIDADE significa tabela
		// no banco de dados.
@Table(name = "postagens") // @table cria pra mim um nome pra minha tabela. semele, fica com o nome do
							// objeto automaticamente
public class Postagem {

	@Id // indica que é uma chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // diz que o ID é autoincrement
	private Long id; // TODO id tem que ser Long com L maiusculo

	@NotNull // porque o título não pode ficar vazio.diz que o campo é obrigatorio ser
				// preenchido
	// poderia ser NotBlank para texto menor, NotNull para maiores.
	// NotBlank tb nao deixa que inicie o texto com espaço. Os dois tbm aceitam uma
	// mensagem p/o usuario. ex NotNull(message="O atributo título é obrigatorio")
	@Size(min = 5, max = 100) // determinar a quantidade de caracteres do título. tbm aceita uma mensagem com
								// uma vírgula depois do numero max(min = 5, max = 10, message = "....")
	private String titulo;

	@NotNull
	@Size(min = 10, max = 100)
	private String texto;

	@UpdateTimestamp // atualiza a data exata da postagem
	private Date date = new java.sql.Date(System.currentTimeMillis()); // metodo para calcular o momento em que foi
																		// escrito
	// podia ser: private LocalDateTime data; ele é automático com a hora, o de cima
	// é manual.
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;//aqui n precisa de Lista igual na classe tema, porque n vai aparecer uma lista de tema pra cada postagem e sim o contrario :)

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}