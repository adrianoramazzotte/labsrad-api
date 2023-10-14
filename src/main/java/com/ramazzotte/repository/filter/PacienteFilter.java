package com.ramazzotte.repository.filter;

import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteFilter {
	
	private String id;	
	private String nome;
    private OffsetDateTime datanascde;
    private OffsetDateTime datanascate;
	private String sexo;	
	private Boolean status;
	private String cpf;
	private String telefone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public OffsetDateTime getDatanascde() {
		return datanascde;
	}
	public void setDatanascde(OffsetDateTime datanascde) {
		this.datanascde = datanascde;
	}
	public OffsetDateTime getDatanascate() {
		return datanascate;
	}
	public void setDatanascate(OffsetDateTime datanascate) {
		this.datanascate = datanascate;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public PacienteFilter(String id, String nome, OffsetDateTime datanascde, OffsetDateTime datanascate, String sexo,
			Boolean status, String cpf, String telefone) {
		this.id = id;
		this.nome = nome;
		this.datanascde = datanascde;
		this.datanascate = datanascate;
		this.sexo = sexo;
		this.status = status;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	public PacienteFilter() {
	}
	
	

}
