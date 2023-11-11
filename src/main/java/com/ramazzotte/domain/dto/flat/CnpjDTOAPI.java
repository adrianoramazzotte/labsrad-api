package com.ramazzotte.domain.dto.flat;

public class CnpjDTOAPI {
	
	private String nome;
	private String cnpj;
	private String logradouro;
	private String numero;
	private String cep;
	private String municipio;
	private String uf;
	private String telefone;
	private String fantasia;
	private String bairro;
	private String complemento;
	


	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
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
	
	
	public CnpjDTOAPI(String nome, String cnpj, String logradouro, String numero, String cep,
			String municipio, String uf, String telefone, String fantasia, String bairro, String complemento) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.municipio = municipio;
		this.uf = uf;
		this.telefone = telefone;
		this.fantasia = fantasia;
		this.bairro = bairro;
		this.complemento = complemento;
	}
	public CnpjDTOAPI() {
	}
	
	

}
