package com.ramazzotte.domain.dto.flat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;

import com.ramazzotte.domain.Empresa;
import com.sun.istack.NotNull;

public class EmpresaFlat {
	
	private Integer id;	
	private String cidade;	
	private String razaosocial;
	private BigDecimal valor;
	@NotNull
	@Column(unique = true)
	private String cpfoucnpj;
	private String naturezapessoa;
	private String uf;
	private String cep;
	private String logradouro;
	private String empresa;	
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	private String numero;
	private String complemento;
	private Boolean editar = Boolean.TRUE;
	private String bairro;
	private String nomecontato;
	private String telefone;
	private String  whats;
	private String email;
	private Boolean status = Boolean.TRUE;
	private OffsetDateTime datagravacao;
	private String emailusuario;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public Boolean getEditar() {
		return editar;
	}
	public void setEditar(Boolean editar) {
		this.editar = editar;
	}
	public String getRazaosocial() {
		return razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getCpfoucnpj() {
		return cpfoucnpj;
	}
	public void setCpfoucnpj(String cpfoucnpj) {
		this.cpfoucnpj = cpfoucnpj;
	}
	public String getNaturezapessoa() {
		return naturezapessoa;
	}
	public void setNaturezapessoa(String naturezapessoa) {
		this.naturezapessoa = naturezapessoa;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNomecontato() {
		return nomecontato;
	}
	public void setNomecontato(String nomecontato) {
		this.nomecontato = nomecontato;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getWhats() {
		return whats;
	}
	public void setWhats(String whats) {
		this.whats = whats;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public EmpresaFlat(Integer id, String cidade, String razaosocial, BigDecimal valor, String cpfoucnpj,
			String naturezapessoa, String uf, String cep, String logradouro, String numero, String complemento,
			String bairro, String nomecontato, String telefone, String whats, String email, Boolean status,
			OffsetDateTime datagravacao, String emailusuario) {
		super();
		this.id = id;
		this.cidade = cidade;
		this.razaosocial = razaosocial;
		this.valor = valor;
		this.cpfoucnpj = cpfoucnpj;
		this.naturezapessoa = naturezapessoa;
		this.uf = uf;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.nomecontato = nomecontato;
		this.telefone = telefone;
		this.whats = whats;
		this.email = email;
		this.status = status;
		this.datagravacao = datagravacao;
		this.emailusuario = emailusuario;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public EmpresaFlat() {
	}
	public EmpresaFlat(Empresa obj) {
		this.id = obj.getId();
		this.cidade = obj.getCidade();
		this.razaosocial = obj.getRazaosocial();
		this.cpfoucnpj = obj.getCpfoucnpj();
		this.naturezapessoa = obj.getNaturezapessoa();
		this.uf = obj.getUf();
		this.cep = obj.getCep();
		this.logradouro = obj.getLogradouro();
		this.numero = obj.getNumero();
		this.complemento = obj.getComplemento();
		this.bairro = obj.getBairro();
		this.nomecontato = obj.getNomecontato();
		this.telefone = obj.getTelefone();
		this.whats = obj.getWhats();
		this.email = obj.getEmail();
		this.status = obj.getStatus();
		this.datagravacao = obj.getLogs().getDatagravacao();
		this.emailusuario = obj.getLogs().getEmailUsuario();
		this.valor = obj.getValor();
	}
	
	

}
