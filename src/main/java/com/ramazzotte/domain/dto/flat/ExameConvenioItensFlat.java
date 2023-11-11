package com.ramazzotte.domain.dto.flat;

import java.math.BigDecimal;

import com.ramazzotte.domain.ExameConvenio;

public class ExameConvenioItensFlat {
	
	
	private Boolean status;
	private BigDecimal preco;
	private Integer idconvenio;
	private String descricaoconvenio;
	private Integer idexame;
	private String descricaoexame;
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
	
	public Integer getIdconvenio() {
		return idconvenio;
	}
	public void setIdconvenio(Integer idconvenio) {
		this.idconvenio = idconvenio;
	}
	public String getDescricaoconvenio() {
		return descricaoconvenio;
	}
	public void setDescricaoconvenio(String descricaoconvenio) {
		this.descricaoconvenio = descricaoconvenio;
	}
	public Integer getIdexame() {
		return idexame;
	}
	public void setIdexame(Integer idexame) {
		this.idexame = idexame;
	}
	public String getDescricaoexame() {
		return descricaoexame;
	}
	public void setDescricaoexame(String descricaoexame) {
		this.descricaoexame = descricaoexame;
	}
	public ExameConvenioItensFlat(Boolean status, BigDecimal preco, Integer idconvenio, String descricaoconvenio,
			Integer idexame, String descricaoexame) {
		this.status = status;
		this.preco = preco;
		this.idconvenio = idconvenio;
		this.descricaoconvenio = descricaoconvenio;
		this.idexame = idexame;
		this.descricaoexame = descricaoexame;
	}
	public ExameConvenioItensFlat() {
	}
	public ExameConvenioItensFlat(ExameConvenio obj) {
		this.status = obj.getStatus();
		this.preco = obj.getPreco();
		this.idconvenio = obj.getId().getConvenio().getId();
		this.descricaoconvenio = obj.getId().getConvenio().getDescricao();
		this.idexame = obj.getId().getExame().getId();
		this.descricaoexame = obj.getId().getExame().getDescricao();
	}
	
	

}
