package com.ramazzotte.domain.dto.rel;

import com.ramazzotte.domain.Convenio;

public class ConvenioRel {
	
	private Integer id;
	private String descricao;
	private String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ConvenioRel(Integer id, String descricao, String status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.status = status;
	}
	public ConvenioRel() {
	
	}
	
	public ConvenioRel(Convenio conv) {
		this.id = conv.getId();
		this.descricao = conv.getDescricao();
		Boolean status = conv.getStatus();
		if(status) {
			this.status = "Ativo";
		}else {
			this.status = "Inativo";
		}
		
	}
	
	
	
}
