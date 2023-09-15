
package com.ramazzotte.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ExameConvenio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	public ExameConvenioPK id = new ExameConvenioPK();
	private Boolean status;
	private BigDecimal preco;
	
	public ExameConvenioPK getId() {
		return id;
	}
	public void setId(ExameConvenioPK id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public ExameConvenio(ExameConvenioPK id, Boolean status, BigDecimal preco) {
		this.id = id;
		this.status = status;
		this.preco = preco;
	}
	public ExameConvenio() {
	}
	@ManyToOne
	private Tenant tenant;
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}	

}
