package com.ramazzotte.repository.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AtendimentoFilter {
	
	private String id;
	private String idficha;
	private String nome;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datanascde;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datanascate;
	private String sexo;
	private String formapagamento;
	private String emailusuario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datalancamentode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datalancamentoate;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdficha() {
		return idficha;
	}
	public void setIdficha(String idficha) {
		this.idficha = idficha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatanascde() {
		return datanascde;
	}
	public void setDatanascde(Date datanascde) {
		this.datanascde = datanascde;
	}
	public Date getDatanascate() {
		return datanascate;
	}
	public void setDatanascate(Date datanascate) {
		this.datanascate = datanascate;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFormapagamento() {
		return formapagamento;
	}
	public void setFormapagamento(String formapagamento) {
		this.formapagamento = formapagamento;
	}
	public String getEmailusuario() {
		return emailusuario;
	}
	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}
	public Date getDatalancamentode() {
		return datalancamentode;
	}
	public void setDatalancamentode(Date datalancamentode) {
		this.datalancamentode = datalancamentode;
	}
	public Date getDatalancamentoate() {
		return datalancamentoate;
	}
	public void setDatalancamentoate(Date datalancamentoate) {
		this.datalancamentoate = datalancamentoate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
