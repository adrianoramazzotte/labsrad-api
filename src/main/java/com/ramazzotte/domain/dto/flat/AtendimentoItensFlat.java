package com.ramazzotte.domain.dto.flat;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.domain.Tenant;

public class AtendimentoItensFlat {
	private int idconvenio;
	private int idexame;
	
	private String descricaoconvenio;
	private String descricaoexame;
	private BigDecimal preco;
	private BigDecimal total;
	private BigDecimal desconto;
	private Integer acesso;
	private Tenant tenant;
	private LocalDate dataatendimento;
	public int getIdconvenio() {
		return idconvenio;
	}
	public void setIdconvenio(int idconvenio) {
		this.idconvenio = idconvenio;
	}
	public int getIdexame() {
		return idexame;
	}
	public void setIdexame(int idexame) {
		this.idexame = idexame;
	}
	public String getDescricaoconvenio() {
		return descricaoconvenio;
	}
	public void setDescricaoconvenio(String descricaoconvenio) {
		this.descricaoconvenio = descricaoconvenio;
	}
	public String getDescricaoexame() {
		return descricaoexame;
	}
	public void setDescricaoexame(String descricaoexame) {
		this.descricaoexame = descricaoexame;
	}
	
	public Integer getAcesso() {
		return acesso;
	}
	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	
	
	public AtendimentoItensFlat(int idconvenio, int idexame, String descricaoconvenio, String descricaoexame,
			BigDecimal preco, BigDecimal total, BigDecimal desconto, LocalDate dataAtendimento, Integer acesso) {
		super();
		this.idconvenio = idconvenio;
		this.idexame = idexame;
		this.descricaoconvenio = descricaoconvenio;
		this.descricaoexame = descricaoexame;
		this.preco = preco;
		this.total = total;
		this.desconto = desconto;
		this.dataatendimento = dataatendimento;
		this.acesso = acesso;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public AtendimentoItensFlat() {
		
	}
	public AtendimentoItensFlat(AtendimentoItens obj) {
		this.idconvenio = obj.getId().getConvenio().getId();
		this.idexame = obj.getId().getExame().getId();
		this.descricaoconvenio = obj.getId().getConvenio().getDescricao();
		this.descricaoexame = obj.getId().getExame().getDescricao();
		this.preco = obj.getPreco();
		this.desconto = obj.getDesconto();
		this.total = obj.getTotal();
		this.dataatendimento = obj.getDataAtendimento();
		this.acesso = obj.getAcesso();
		this.tenant = obj.getTenant();
	}

	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	@Override
	public String toString() {
		return "AtendimentoItensFlat [idConvenio=" + idconvenio + ", idExame=" + idexame + ", descricaoConvenio="
				+ descricaoconvenio + ", descricaoExame=" + descricaoexame + ", preco=" + preco + ", total=" + total
				+ ", desconto=" + desconto + ", acesso=" + acesso + ", dataAtendimento=" + dataatendimento + "]";
	}
	public LocalDate getDataatendimento() {
		return dataatendimento;
	}
	public void setDataatendimento(LocalDate dataatendimento) {
		this.dataatendimento = dataatendimento;
	}
	
	
	
}
