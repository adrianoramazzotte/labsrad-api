package com.ramazzotte.domain.dto.flat;

import java.time.OffsetDateTime;

import com.ramazzotte.domain.Convenio;

public class ConvenioFlat {
	private Integer id;	
	private String descricao;
	private OffsetDateTime datagravacao;
	private String emailusuario;
	private Boolean status = Boolean.TRUE;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public ConvenioFlat(Integer id, String descricao, OffsetDateTime datagravacao,
			String emailusuario, Boolean status) {
		this.id = id;
		this.descricao = descricao;
		this.datagravacao = datagravacao;
		this.emailusuario = emailusuario;
		this.status = status;
	}
	public ConvenioFlat() {	}
	public ConvenioFlat(Convenio conv) {
		this.id = conv.getId();
		this.descricao = conv.getDescricao();
		this.datagravacao = conv.getLogs().getDatagravacao();
		this.emailusuario = conv.getLogs().getEmailUsuario();
		this.status = conv.getStatus();
		
		
	}
	
	

	
}
