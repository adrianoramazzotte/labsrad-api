package com.ramazzotte.domain.dto.flat;

import java.math.BigDecimal;
import java.util.Date;

import com.ramazzotte.domain.ExameConvenio;

public class ExameFlatConvenio {
	
	private Integer idconvenio;	
	private String descricaoconvenio;
	private BigDecimal preco;
	private Date datagravacao;
	private String emailusuario;
	
	
	public Date getDatagravacao() {
		return datagravacao;
	}
	public void setDatagravacao(Date datagravacao) {
		this.datagravacao = datagravacao;
	}
	public String getEmailusuario() {
		return emailusuario;
	}
	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
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
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public ExameFlatConvenio(Integer idconvenio, String descricaoconvenio, BigDecimal preco) {
		this.idconvenio = idconvenio;
		this.descricaoconvenio = descricaoconvenio;
		this.preco = preco;
	}
	public ExameFlatConvenio() {
	}
	public ExameFlatConvenio(ExameConvenio obj) {
		this.idconvenio = obj.getId().getConvenio().getId();
		this.descricaoconvenio = obj.getId().getConvenio().getDescricao();
		this.preco = obj.getPreco();
	
	}
	@Override
	public String toString() {
		return "ExameFlatConvenio [idConvenio=" + idconvenio + ", descricaoConvenio=" + descricaoconvenio + ", preco="
				+ preco + "]";
	}

	

}
