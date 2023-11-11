package com.ramazzotte.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.validation.exame.ExameUpdate;
@ExameUpdate
public class ExameDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="atendimento_id", nullable = false)
  	private Atendimento atendimento;
	private String descricao;
	private BigDecimal valor;
	private String convenio;
	
	

	public ExameDTO(Integer id, Atendimento atendimento, String descricao, BigDecimal valor, String convenio,
			Boolean status) {
		super();
		this.id = id;
		this.atendimento = atendimento;
		this.descricao = descricao;
		this.valor = valor;
		this.convenio = convenio;
		this.status = status;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
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
	

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	

	@Override
	public String toString() {
		return "ExameDTO [id=" + id + ", atendimento=" + atendimento + ", descricao=" + descricao + ", valor=" + valor
				+ ", convenio=" + convenio + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExameDTO other = (ExameDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ExameDTO(Integer id, String descricao, BigDecimal valor, Boolean status) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.status = status;
	}
	public ExameDTO() {
		
	}
	
	

}
