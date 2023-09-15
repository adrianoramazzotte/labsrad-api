package com.ramazzotte.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class UsuarioEmpresaPK implements Serializable{
	private static final long serialVersionUID = 1L;
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_empresa")
	private Empresa emrpesa;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Empresa getEmrpesa() {
		return emrpesa;
	}
	public void setEmrpesa(Empresa emrpesa) {
		this.emrpesa = emrpesa;
	}
	public UsuarioEmpresaPK() {
		super();
	}
	public UsuarioEmpresaPK(Usuario usuario, Empresa emrpesa) {
		super();
		this.usuario = usuario;
		this.emrpesa = emrpesa;
	}

}
