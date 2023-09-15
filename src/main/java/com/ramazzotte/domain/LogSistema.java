package com.ramazzotte.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class LogSistema implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String comando;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss:SSS")
	@Column(columnDefinition = "datetime")    
	private OffsetDateTime datagravacao;	
	private String emailUsuario;
	private Boolean status;
	@JsonIgnore
	@ManyToOne
	private Tenant tenant;
	
	
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}

	public OffsetDateTime getDatagravacao() {
		return datagravacao;
	}
	public void setDatagravacao(OffsetDateTime datagravacao) {
		this.datagravacao = datagravacao;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public LogSistema(Integer id, String comando, OffsetDateTime datagravacao, String emailUsuario) {
		super();
		this.id = id;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.emailUsuario = emailUsuario;
		this.status = true;
	}	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="paciente_id", nullable = false)
  	private Paciente paciente;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="patrimonio_id", nullable = false)
  	private Patrimonio patrimonio;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="atendimento_id", nullable = false)
  	private Atendimento atendimento;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="empresa_id", nullable = false)
  	private Empresa empresa;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="exame_id", nullable = false)
  	private Exame exame;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="permissao_id", nullable = false)
  	private Permissao permissao;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="usuario_id", nullable = false)
  	private Usuario usuario;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="convenio_id", nullable = false)
  	private Convenio convenio;
	
	public LogSistema() {

	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	
	public Patrimonio getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(Patrimonio patrimonio) {
		this.patrimonio = patrimonio;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
    public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Convenio conv) {
    	this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.convenio = conv;
		this.status = true;
	}
    public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Empresa empresa) {
    	this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.empresa = empresa;
		this.status = true;
	}
	public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Exame exame) {
		this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.exame = exame;
		this.status = true;
	}
	public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Paciente paciente) {
		this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.paciente = paciente;
		this.status = true;
	}
	public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Atendimento atendimento) {
		this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.atendimento = atendimento;
		this.status = true;
	}
	public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Usuario usu) {
		this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.usuario = usu;
		this.status = true;
	}
	
	public LogSistema(Integer id, String usuarioLogado, String comando, OffsetDateTime datagravacao, Patrimonio patrimonio) {
		this.id = id;
		this.emailUsuario = usuarioLogado;
		this.comando = comando;
		this.datagravacao = datagravacao;
		this.patrimonio = patrimonio;
		this.status = true;
	}
	
	
	
	
	
}