package com.ramazzotte.domain.dto.flat;

import java.math.BigDecimal;

import com.ramazzotte.domain.ExameConvenio;

public class ExameConvenioItensFlat2 {
	
	
	private Boolean status;
	private BigDecimal preco;
	private Integer id;
	private String descricao;
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
	public ExameConvenioItensFlat2(Boolean status, BigDecimal preco, Integer id, String descricao,
			Integer idexame, String descricaoexame) {
		this.status = status;
		this.preco = preco;
		this.id = id;
		this.descricao = descricao;
		this.idexame = idexame;
		this.descricaoexame = descricaoexame;
	}
	public ExameConvenioItensFlat2() {
	}
	public ExameConvenioItensFlat2(ExameConvenio obj) {
		this.status = obj.getStatus();
		this.preco = obj.getPreco();
		this.id = obj.getId().getConvenio().getId();
		this.descricao = obj.getId().getConvenio().getDescricao();
		this.idexame = obj.getId().getExame().getId();
		this.descricaoexame = obj.getId().getExame().getDescricao();
	}
	
	

}
