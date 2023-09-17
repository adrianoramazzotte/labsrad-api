package com.ramazzotte.domain.exercicio;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Item implements Serializable{
	
	
	@EmbeddedId
	public ProdutoPedidoPK id = new ProdutoPedidoPK();


	private Integer quantidade;
	private Boolean status;
	public Item() {
		super();
	}
	public Item(ProdutoPedidoPK id, Integer quantidade, Boolean status) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.status = status;
	}
	public ProdutoPedidoPK getId() {
		return id;
	}
	public void setId(ProdutoPedidoPK id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	
}
