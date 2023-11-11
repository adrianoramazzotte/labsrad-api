package com.ramazzotte.domain.dto.flat;

public class CNPJDTOFlat {
	private String cnaeCode;
	private String cnaeText;
	private String razao;
	private String cnpj;
	private String logradouro;
	private String naturezaPessoa;
	private String numero;
	private String cep;
	private String municipio;
	private String uf;
	private String telefone;
	private String fantasia;
	private String bairro;
	private String complemento;
	public String getCnaeCode() {
		return cnaeCode;
	}
	
	public String getNaturezaPessoa() {
		return naturezaPessoa;
	}

	public void setNaturezaPessoa(String naturezaPessoa) {
		this.naturezaPessoa = naturezaPessoa;
	}

	public void setCnaeCode(String cnaeCode) {
		this.cnaeCode = cnaeCode;
	}
	public String getCnaeText() {
		return cnaeText;
	}
	public void setCnaeText(String cnaeText) {
		this.cnaeText = cnaeText;
	}

	public String getRazao() {
		return razao;
	}
	public void setRazao(String razao) {
		this.razao = razao;
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
	public CNPJDTOFlat(String cnaeCode, String cnaeText, String razao, String cnpj, String logradouro, String numero,
			String cep, String municipio, String uf, String telefone, String fantasia, String bairro,
			String complemento) {
		this.cnaeCode = cnaeCode;
		this.cnaeText = cnaeText;
		this.razao = razao;
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
		this.naturezaPessoa = "Juridica";
	}
	public CNPJDTOFlat() {
		
	}
//	public CNPJDTOFlat(CnpjDTOAPI obj) {
//		
//		this.razao = obj.getNome();
//		this.cnpj = obj.getCnpj();
//		this.logradouro = obj.getLogradouro();
//		this.numero = obj.getNumero();
//		this.cep = obj.getCep().replace("." , ""); //tira ponto
//		this.municipio = obj.getMunicipio();
//		this.uf = obj.getUf();
//		this.telefone = obj.getTelefone().replace("(" , "").replace(")" , "").replace(" " , "").replace("-" , "");
//		this.fantasia = obj.getFantasia();
//		this.bairro = obj.getBairro();
//		this.complemento = obj.getComplemento();	
//		this.naturezaPessoa = "Juridica";
//		
//	}

	public CNPJDTOFlat(CnpjDTOAPI obj) {
		this.razao = obj.getNome();
		this.cnpj = obj.getCnpj();
		this.logradouro = obj.getLogradouro();
		this.numero = obj.getNumero();
		this.cep = obj.getCep().replace("." , ""); //tira ponto
		this.municipio = obj.getMunicipio();
		this.uf = obj.getUf();
		this.telefone = obj.getTelefone().replace("(" , "").replace(")" , "").replace(" " , "").replace("-" , "");
		this.fantasia = obj.getFantasia();
		this.bairro = obj.getBairro();
		this.complemento = obj.getComplemento();	
		this.naturezaPessoa = "JURIDICA";
		
	}

   
	

	
	
	

}
