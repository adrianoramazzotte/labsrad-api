package com.ramazzotte.domain.dto.rel;

import java.io.Serializable;
import java.math.BigDecimal;

public class RelGraficoExamePorUsuario implements Serializable{
	private static final long serialVersionUID = 1L;
	private BigDecimal valor;
	private Integer mes;
	private Integer ano;
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public RelGraficoExamePorUsuario(BigDecimal valor, Integer mes, Integer ano) {
		super();
		this.valor = valor;
		this.mes = mes;
		this.ano = ano;
	}
	
	public RelGraficoExamePorUsuario() {
		
	}

	
	
}
