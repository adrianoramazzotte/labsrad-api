package com.ramazzotte.domain.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.validation.ConvenioInsert;
@ConvenioInsert
public class ConvenioNewDTO {
	
	private Integer id;		
	private String descricao;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public ConvenioNewDTO(Integer id, String descricao, Boolean status) {
		this.id = id;
		this.descricao = descricao;
		this.status = status;
	}
	public ConvenioNewDTO() {
	}
	

}
