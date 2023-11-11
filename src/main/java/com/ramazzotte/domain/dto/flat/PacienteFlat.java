package com.ramazzotte.domain.dto.flat;

import java.time.OffsetDateTime;
import java.util.Date;

import com.ramazzotte.domain.Paciente;

public class PacienteFlat {
	
	private Integer id;	
	private String nome;
	private Date datanasc;
	private String sexo;
	private Boolean status = Boolean.TRUE;
	private OffsetDateTime datagravacao;
	private String emailusuario;
	private String cpf;
	private String telefone;
	
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
	public PacienteFlat(Integer id, String nome, Date datanasc, String sexo, Boolean status, OffsetDateTime dataGravacao,
			String emailusuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.datanasc = datanasc;
		this.sexo = sexo;
		this.status = status;
		this.datagravacao = datagravacao;
		this.emailusuario = emailusuario;
	}
	
	public PacienteFlat() {
	}
	public PacienteFlat(Paciente pac, String  ok) {
		this.id = pac.getId();
		this.nome = pac.getNome();
//		this.datanasc = pac.getDatanasc();
//		this.sexo = pac.getSexo();
//		this.status = pac.getStatus();
//		this.datagravacao = pac.getLogs().getDatagravacao();
//		this.emailusuario = pac.getLogs().getEmailUsuario();	
//		this.telefone = pac.getTelefone();
//		this.cpf = pac.getCpf();
	}
	public PacienteFlat(Paciente pac) {
		this.id = pac.getId();
		this.nome = pac.getNome();
		this.datanasc = pac.getDatanasc();
		this.sexo = pac.getSexo();
		this.status = pac.getStatus();
		this.datagravacao = pac.getLogs().getDatagravacao();
		this.emailusuario = pac.getLogs().getEmailUsuario();	
		this.telefone = pac.getTelefone();
		this.cpf = pac.getCpf();
	}

	

}
