package com.ramazzotte.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	private String descricao;
	private Boolean status;
	private BigDecimal valor;
	private OffsetDateTime vencimento;
    @ManyToOne
	private Categoria categoria;
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public OffsetDateTime getVencimento() {
		return vencimento;
	}
	public void setVencimento(OffsetDateTime vencimento) {
		this.vencimento = vencimento;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Produto(Integer id, String descricao, Boolean status, BigDecimal valor, OffsetDateTime vencimento,
			Categoria categoria) {
		this.id = id;
		this.descricao = descricao;
		this.status = status;
		this.valor = valor;
		this.vencimento = vencimento;
		this.categoria = categoria;
	}
    
	public Produto() {

	}
	
    
	

}
