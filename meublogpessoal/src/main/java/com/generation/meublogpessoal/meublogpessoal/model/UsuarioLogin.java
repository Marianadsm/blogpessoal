package com.generation.meublogpessoal.meublogpessoal.model;
//não é uma entidade/tabela. é um objeto de APOIO pra pessoa logar e porque o token n é fixo. é variável
public class UsuarioLogin {
private Long id;
private String nome;
private String usuario;
private String senha;
private String foto;
private String token;//infos de usuario e senha. se tiver Token significa q está logado. só quem tem essa chave  pode fazer o que quiser na aplicação. Se n tiver, tem algumas autorizações apenas(como cadastro)

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}
public String getFoto() {
	return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}

}
