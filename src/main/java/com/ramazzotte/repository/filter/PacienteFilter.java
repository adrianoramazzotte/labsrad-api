package com.ramazzotte.repository.filter;

import java.time.OffsetDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteFilter {
	
	
	private String id;
	private String cpf;
	private String nome;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datanascde;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datanascate;
	private String sexo;
	private String emailusuario;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datagravacaode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datagravacaoate;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public String getEmailusuario() {
		return emailusuario;
	}
	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}
	public Date getDatagravacaode() {
		return datagravacaode;
	}
	public void setDatagravacaode(Date datagravacaode) {
		this.datagravacaode = datagravacaode;
	}
	public Date getDatagravacaoate() {
		return datagravacaoate;
	}
	public void setDatagravacaoate(Date datagravacaoate) {
		this.datagravacaoate = datagravacaoate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
