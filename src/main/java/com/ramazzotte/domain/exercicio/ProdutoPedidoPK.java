package com.ramazzotte.domain.exercicio;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Embeddable
public class ProdutoPedidoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ProdutoPedidoPK(Produto produto, Pedido pedido) {
		super();
		this.produto = produto;
		this.pedido = pedido;
	}

	public ProdutoPedidoPK() {
		super();
	}

	
	
}
