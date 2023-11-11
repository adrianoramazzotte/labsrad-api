package com.ramazzotte.domain.dto.rel;

import java.math.BigDecimal;

import com.ramazzotte.domain.ExameConvenio;

public class ExameRel {
	
	private String exame;
	private String convenio;
	private BigDecimal preco;
	private String status;
	

	public ExameRel(String exame, String convenio, BigDecimal preco, String status) {
		super();
		this.exame = exame;
		this.convenio = convenio;
		this.preco = preco;
		this.status = status;
	}
	public ExameRel() {
		}


	public String getExame() {
		return exame;
	}


	public void setExame(String exame) {
		this.exame = exame;
	}


	public String getConvenio() {
		return convenio;
	}


	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public ExameRel(ExameConvenio exa) {
		this.exame = exa.getId().getExame().getDescricao();
		this.convenio = exa.getId().getConvenio().getDescricao();
		this.preco = exa.getPreco();
		Boolean status = exa.getStatus();
		if(status) {
			this.status = "Ativo";
		}else {
			this.status = "Inativo";
		}
			
		
	}
	
	
	

}
