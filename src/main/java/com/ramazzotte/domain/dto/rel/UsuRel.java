package com.ramazzotte.domain.dto.rel;

import com.ramazzotte.domain.Usuario;

public class UsuRel {

	@Override
	public String toString() {
		return "UsuRel [nome=" + nome + ", email=" + email + ", status=" + status + "]";
	}
	private String nome;
	private String email;
	private String status;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UsuRel(String nome, String email, String status) {
		super();
		this.nome = nome;
		this.email = email;
		this.status = status;
	}
	public UsuRel(Usuario usu) {
		this.nome = usu.getNome();
		this.email = usu.getEmail();
		Boolean status = usu.getStatus();
		if(status) {
			this.status = "Ativo";
		}else {
			this.status = "Inativo";
		}
			
		
	}

	
	
	
}
