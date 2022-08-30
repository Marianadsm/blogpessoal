package com.generation.meublogpessoal.meublogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity //entidade  Table, chamada POSTAGEM
@Table (name="postagens")
public class Postagem {
	
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) //diz que o ID é autoincrement
	private long id;
	
	@NotNull //porque o título não pode ficar vazio
	@Size(min = 5, max = 100)//determinar a quantidade de caracteres do título
	private String titulo;
	
	@NotNull 
	@Size(min= 10, max = 100)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) //pois é TEMPO e timestamp é o tipo de tempo
	private Date date = new java.sql.Date(System.currentTimeMillis()); //metodo para calcular o momento em que foi escrito

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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

}