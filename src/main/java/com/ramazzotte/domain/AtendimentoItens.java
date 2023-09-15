package com.ramazzotte.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class AtendimentoItens implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	public AtendimentoItensPK id = new AtendimentoItensPK();
	
	private BigDecimal desconto;
	private BigDecimal preco;
	private BigDecimal total;
	private Integer acesso;
	public Integer getAcesso() {
		return acesso;
	}

	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}

	@DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(columnDefinition = "date")    
    private LocalDate dataAtendimento;
	
	private Boolean status = Boolean.TRUE;
	
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}



	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public AtendimentoItensPK getId() {
		return id;
	}

	public void setId(AtendimentoItensPK id) {
		this.id = id;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public AtendimentoItens(AtendimentoItensPK id, BigDecimal desconto) {
		this.id = id;
		this.desconto = desconto;
	}

	public AtendimentoItens() {
	}
	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	@Override
	public String toString() {
		return "AtendimentoItens [id=" + id + ", desconto=" + desconto + ", preco=" + preco + ", total=" + total
				+ ", acesso=" + acesso + ", dataAtendimento=" + dataAtendimento + ", status=" + status + "]";
	}

	public AtendimentoItens(Exame exame, Atendimento atendimento, BigDecimal desconto, BigDecimal preco,
			LocalDate dataAtendimento, Boolean status, Integer acesso) {
		id.setExame(exame);
		id.setAtendimento(atendimento);
		this.desconto = desconto;
		this.preco = preco;
		this.dataAtendimento = dataAtendimento;
		this.status = status;
		this.acesso = acesso;
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
