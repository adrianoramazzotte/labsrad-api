package com.ramazzotte.domain.dto.flat;

import java.time.OffsetDateTime;
import java.util.List;

import com.ramazzotte.domain.Usuario;
import com.ramazzotte.domain.dto.EmpresaUsu;

public class UsuarioFlat {
	
	private Integer id;	
	private Boolean status = Boolean.TRUE;
	private String nome;
	private String login;
	private String email;
	private String perfil;
	private String senha;
	private OffsetDateTime datagravacao;
	private String emailusuario;	


	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}



	public UsuarioFlat() {
		super();
	}

	public OffsetDateTime getDatagravacao() {
		return datagravacao;
	}

	public void setDatagravacao(OffsetDateTime datagravacao) {
		this.datagravacao = datagravacao;
	}

	public String getEmailusuario() {
		return emailusuario;
	}

	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private List<PermissaoFront> permissoes;
	private List<EmpresaUsu> empresas;

	public List<EmpresaUsu> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<EmpresaUsu> empresas) {
		this.empresas = empresas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PermissaoFront> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoFront> permissoes) {
		this.permissoes = permissoes;
	}



	public UsuarioFlat(Integer id, Boolean status, String nome, String login, String email, String senha,
			OffsetDateTime dataGravacao, String emailusuario, List<PermissaoFront> permissoes) {
		super();
		this.id = id;
		this.status = status;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.datagravacao = dataGravacao;
		this.emailusuario = emailusuario;
		this.permissoes = permissoes;
	}

	public UsuarioFlat(Usuario usu) {
		this.id = usu.getId();
		this.status = usu.getStatus();
		this.nome = usu.getNome();
		this.login = usu.getLogin();
		this.email = usu.getEmail();
		//this.senha = senha;
		this.datagravacao = usu.getLogs().getDatagravacao();
		this.emailusuario = usu.getLogs().getEmailUsuario();
		//this.permissoes = permissoes;
	}

	@Override
	public String toString() {
		return "UsuarioDtoFlat [id=" + id + ", status=" + status + ", nome=" + nome + ", login=" + login + ", email="
				+ email + ", permissoes=" + permissoes + "]";
	}
	
	

}
