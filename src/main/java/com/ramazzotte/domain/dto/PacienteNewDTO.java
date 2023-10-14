package com.ramazzotte.domain.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ramazzotte.validation.paciente.PacienteInsert;
@PacienteInsert
public class PacienteNewDTO {
	
	private String nome;
	@DateTimeFormat(pattern = "yyyy-mm-dd")  
    private Date datanasc;
	private String sexo;	
	private Boolean status;
	private String cpf;
	private String telefone;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatanasc() {
		return datanasc;
	}
	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
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
	public PacienteNewDTO() {
	}
	public PacienteNewDTO(String nome, Date datanasc, String sexo, Boolean status, String cpf, String telefone) {
		this.nome = nome;
		this.datanasc = datanasc;
		this.sexo = sexo;
		this.status = status;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	

}
