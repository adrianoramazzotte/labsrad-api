package com.ramazzotte.domain.dto.flat;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramazzotte.domain.Classepermissao;

public class PermissaoFront {
	private int id;
	private String nome;
	private PermissoesFlat permission;
	
	@JsonIgnore
	@ManyToOne
 	private Classepermissao classepermissao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public PermissoesFlat getPermission() {
		return permission;
	}
	public void setPermission(PermissoesFlat permission) {
		this.permission = permission;
	}
	public PermissaoFront(int id, String nome, PermissoesFlat permission) {
	
		this.id = id;
		this.nome = nome;
		this.permission = permission;
	}
	public PermissaoFront() {
	
	}
	@Override
	public String toString() {
		return "PermissaoFront [id=" + id + ", nome=" + nome + ", permission=" + permission + "]";
	}
	
	

}
