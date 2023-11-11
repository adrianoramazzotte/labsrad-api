package com.ramazzotte.domain.dto.flat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ramazzotte.domain.Patrimonio;

public class PatrimonioFlat {

	private Integer id;

	private String descricao;
	private OffsetDateTime dataaquisicao;
	private BigDecimal valor;
	private Boolean status = Boolean.TRUE;
	private Integer idempresa;
	private String descempresa;
	private OffsetDateTime datagravacao;
	private String emailusuario;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public OffsetDateTime getDatagravacao() {
		return datagravacao;
	}
	public void setDatagravacao(OffsetDateTime datagravacao) {
		this.datagravacao = datagravacao;
	}
	public String getEmailusuario() {
		return emailusuario;
	}
	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}
	public OffsetDateTime getDataaquisicao() {
		return dataaquisicao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public OffsetDateTime dataaquisicao() {
		return dataaquisicao;
	}
	public void setDataaquisicao(OffsetDateTime dataaquisicao) {
		this.dataaquisicao = dataaquisicao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getIdempresa() {
		return idempresa;
	}
	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}
	public String getDescempresa() {
		return descempresa;
	}
	public void setDescempresa(String descempresa) {
		this.descempresa = descempresa;
	}
	public PatrimonioFlat(Integer id, String descricao, OffsetDateTime dataaquisicao, BigDecimal valor, Boolean status,
			Integer idempresa, String descempresa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataaquisicao = dataaquisicao;
		this.valor = valor;
		this.status = status;
		this.idempresa = idempresa;
		this.descempresa = descempresa;
	}
	public PatrimonioFlat() {

	}
	
	public PatrimonioFlat(Patrimonio obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.dataaquisicao = obj.getDataaquisicao();
		this.valor = obj.getValor();
		this.status = obj.getStatus();
		this.idempresa = obj.getEmpresa().getId();
		this.descempresa = obj.getEmpresa().getRazaosocial();
		this.emailusuario = obj.getLogs().getEmailUsuario();
		this.datagravacao = obj.getLogs().getDatagravacao();
	}
	
	

}
