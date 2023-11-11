package com.ramazzotte.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.validation.exame.ExameInsert;
@ExameInsert
public class ExameNewDTO implements Serializable{
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
	
	@OneToMany(mappedBy = "id.exame")
	private Set<AtendimentoItens> itensAtendimento = new HashSet<>();	
	@OneToMany(mappedBy = "id.exame")
	private Set<ExameConvenio> examesConvenio = new HashSet<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	public String getDescricao() {
		return descricao;
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
	public Set<AtendimentoItens> getItensAtendimento() {
		return itensAtendimento;
	}
	public void setItensAtendimento(Set<AtendimentoItens> itensAtendimento) {
		this.itensAtendimento = itensAtendimento;
	}
	public Set<ExameConvenio> getExamesConvenio() {
		return examesConvenio;
	}
	public void setExamesConvenio(Set<ExameConvenio> examesConvenio) {
		this.examesConvenio = examesConvenio;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ExameNewDTO(Integer id, Atendimento atendimento, String descricao, BigDecimal valor,
			Set<AtendimentoItens> itensAtendimento, Set<ExameConvenio> examesConvenio) {
		this.id = id;
		this.atendimento = atendimento;
		this.descricao = descricao;
		this.valor = valor;
		this.itensAtendimento = itensAtendimento;
		this.examesConvenio = examesConvenio;
	}
	public ExameNewDTO() {
	}
	@Override
	public String toString() {
		return "ExameNewDTO [id=" + id + ", atendimento=" + atendimento + ", descricao=" + descricao + ", valor="
				+ valor + ", itensAtendimento=" + itensAtendimento + ", examesConvenio=" + examesConvenio + "]";
	}
	


}
