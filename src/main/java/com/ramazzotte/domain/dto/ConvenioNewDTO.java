package com.ramazzotte.domain.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.validation.convenio.ConvenioInsert;
@ConvenioInsert
public class ConvenioNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@OneToMany(mappedBy = "id.convenio")
	private Set<AtendimentoItens> itensAtendimento = new HashSet<>();
	



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
	@Override
	public String toString() {
		return "Convenio [id=" + id + ", descricao=" + descricao + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
	
}