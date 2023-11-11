package com.ramazzotte.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.ramazzotte.validation.paciente.PacienteUpdate;
@PacienteUpdate
public class PacienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	

	private String nome;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(columnDefinition = "date")    
    private LocalDate datanasc;
	private String sexo;
	private String cpf;
	private String telefone;
	private Boolean status = Boolean.TRUE;

	
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDatanasc() {
		return datanasc;
	}
	public void setDatanasc(LocalDate datanasc) {
		this.datanasc = datanasc;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public PacienteDTO(Integer id, String nome, LocalDate datanasc, String sexo) {
	
		this.id = id;
		this.nome = nome;
		this.datanasc = datanasc;
		this.sexo = sexo;
	}
	public PacienteDTO() {
		
	}
	
	
	

}
