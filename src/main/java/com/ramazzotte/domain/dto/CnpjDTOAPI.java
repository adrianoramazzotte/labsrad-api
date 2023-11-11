package com.ramazzotte.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class CnpjDTOAPI {
	private List<AtividadeCnae> atividade_principal = new ArrayList<AtividadeCnae>();
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
	private String naturezaPessoa;
	private String cnae_fiscal_descricao;
	private String cnae_fiscal;
	private String nome_fantasia;
	private String razao_social;
	


	public String getNaturezaPessoa() {
		return naturezaPessoa;
	}
	public void setNaturezaPessoa(String naturezaPessoa) {
		this.naturezaPessoa = naturezaPessoa;
	}
	public String getCnae_fiscal_descricao() {
		return cnae_fiscal_descricao;
	}
	public void setCnae_fiscal_descricao(String cnae_fiscal_descricao) {
		this.cnae_fiscal_descricao = cnae_fiscal_descricao;
	}
	public String getCnae_fiscal() {
		return cnae_fiscal;
	}
	public void setCnae_fiscal(String cnae_fiscal) {
		this.cnae_fiscal = cnae_fiscal;
	}
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public List<AtividadeCnae> getAtividade_principal() {
		return atividade_principal;
	}
	public void setAtividade_principal(List<AtividadeCnae> atividade_principal) {
		this.atividade_principal = atividade_principal;
	}
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
