package com.ramazzotte.domain.dto;

public class CnpjFront {
	

	private String nome = "";
	private String cnpj= "";
	private String logradouro= "";
	private String numero= "";
	private String cep= "";
	private String municipio= "";
	private String uf= "";
	private String telefone= "";
	private String fantasia= "";
	private String razaosocial= "";
	private String bairro= "";
	private String cnaefiscal= "";
	private String cnaefiscaldescricao= "";
	private String complemento= "";
	private String naturezapessoa= "";
	
	
	public String getNaturezapessoa() {
		return naturezapessoa;
	}
	public void setNaturezapessoa(String naturezapessoa) {
		this.naturezapessoa = naturezapessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getRazaosocial() {
		return razaosocial;
	}
	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCnaefiscal() {
		return cnaefiscal;
	}
	public void setCnaefiscal(String cnaefiscal) {
		this.cnaefiscal = cnaefiscal;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public CnpjFront(String nome, String cnpj, String logradouro,
			String numero, String cep, String municipio, String uf, String telefone, String fantasia,
			String razaosocial, String bairro, String cnaefiscal, String complemento) {
		super();

		this.nome = nome;
		this.cnpj = cnpj;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.municipio = municipio;
		this.uf = uf;
		this.telefone = telefone;
		this.fantasia = fantasia;
		this.razaosocial = razaosocial;
		this.bairro = bairro;
		this.cnaefiscal = cnaefiscal;
		this.complemento = complemento;
	}
	public CnpjFront(CnpjDTOAPI obj) {
		this.nome = obj.getNome();
		this.cnpj = obj.getCnpj();
		this.logradouro = obj.getLogradouro();
		this.numero = obj.getNumero();
		this.cep = obj.getCep();
		this.municipio = obj.getMunicipio();
		this.uf = obj.getUf();
		this.telefone = obj.getTelefone();
		this.fantasia = obj.getFantasia();
		this.razaosocial = obj.getRazao_social();
		this.bairro = obj.getBairro();
		this.cnaefiscal = obj.getCnae_fiscal();
		this.complemento = obj.getComplemento();
		this.cnaefiscaldescricao = obj.getCnae_fiscal_descricao();
		this.razaosocial = obj.getRazao_social();
		this.naturezapessoa = "JURIDICA";
	}
	
	public String getCnaefiscaldescricao() {
		return cnaefiscaldescricao;
	}
	public void setCnaefiscaldescricao(String cnaefiscaldescricao) {
		this.cnaefiscaldescricao = cnaefiscaldescricao;
	}

	
	

}
