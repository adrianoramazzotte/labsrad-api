package com.ramazzotte.domain.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

public class EmpresaUsu {
	

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;	
		private String cidade;	
		private String razao;
		@NotNull
		@Column(unique = true)
		private String cpfoucnpj;
		private String naturezaPessoa;
		private String uf;
		private BigDecimal valor;
		private String cep;
		private String logradouro;
		private String numero;
		private String complemento;
		private String bairro;
		private String nomecontato;
		private String telefone;
		private String  whats;
		private String email;
		private Boolean status = Boolean.TRUE;	
		public Boolean empresapadrao;
		public Boolean empresasusuario;
		
		public Boolean getEmpresasusuario() {
			return empresasusuario;
		}
		public void setEmpresasUsuario(Boolean empresasusuario) {
			this.empresasusuario = empresasusuario;
		}
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
		public String getRazao() {
			return razao;
		}
		public void setRazao(String razao) {
			this.razao = razao;
		}
		public String getCpfoucnpj() {
			return cpfoucnpj;
		}
		public void setCpfoucnpj(String cpfoucnpj) {
			this.cpfoucnpj = cpfoucnpj;
		}
		public String getNaturezaPessoa() {
			return naturezaPessoa;
		}
		public void setNaturezaPessoa(String naturezaPessoa) {
			this.naturezaPessoa = naturezaPessoa;
		}
		public String getUf() {
			return uf;
		}
		public void setUf(String uf) {
			this.uf = uf;
		}
		public BigDecimal getValor() {
			return valor;
		}
		public void setValor(BigDecimal valor) {
			this.valor = valor;
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
		public Boolean getEmpresapadrao() {
			return empresapadrao;
		}
		public void setEmpresaPadrao(Boolean empresapadrao) {
			this.empresapadrao = empresapadrao;
		}
		public EmpresaUsu(Integer id, String cidade, String razao, String cpfoucnpj, String naturezaPessoa, String uf,
				BigDecimal valor, String cep, String logradouro, String numero, String complemento, String bairro,
				String nomecontato, String telefone, String whats, String email, Boolean status,
				Boolean empresapadrao) {
			super();
			this.id = id;
			this.cidade = cidade;
			this.razao = razao;
			this.cpfoucnpj = cpfoucnpj;
			this.naturezaPessoa = naturezaPessoa;
			this.uf = uf;
			this.valor = valor;
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
			this.empresapadrao = empresapadrao;
		}
		public EmpresaUsu() {
		}
		
		
		

}
