package com.ramazzotte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity

public class Usuario implements Serializable {
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", status=" + status + ", nome=" + nome + ", login=" + login + ", email=" + email
				+ ", permissoes=" + permissoes + ", senha=" + senha + "]";
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean status = Boolean.TRUE;
	private String nome;
	private String login;
	private String email;	
	private Integer tenantativo;
	private Integer gtenantativo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private List<Permissao> permissoes= new ArrayList<Permissao>();

    @JsonIgnore
	private String senha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTenantativo() {
		return tenantativo;
	}

	public void setTenantativo(Integer tenantativo) {
		this.tenantativo = tenantativo;
	}

	public Integer getGtenantAtivo() {
		return gtenantativo;
	}

	public void setGtenantativo(Integer gtenantativo) {
		this.gtenantativo = gtenantativo;
	}


	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}



	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario(Integer id, Boolean status, String nome, String login, String email, List<Permissao> permissoes,
			List<LogSistema> logs, String senha) {
		this.id = id;
		this.status = status;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.permissoes = permissoes;
		this.logs = logs;
		this.senha = senha;
	}

	public Usuario() {
	}


	
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<LogSistema> logs = new ArrayList<LogSistema>();

	public void addLogs(LogSistema log) {
		logs.add(log);
	}

	public LogSistema getLogs() {
        Integer codigo = 0;
		Integer indice = -1;
		LogSistema ultimo = new LogSistema();
		for (int i = 0; i < logs.size(); i++) {
			if (codigo < logs.get(i).getId()) {
				codigo = logs.get(i).getId();
				indice = i;
			}
		}
		if (indice==-1) {
			return ultimo;
		}else {
			return ultimo = logs.get(indice);
		}
		
	}

	public void setLogs(List<LogSistema> logs) {
		this.logs = logs;
	}

	@ManyToOne
	private Tenant tenant;
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	

}