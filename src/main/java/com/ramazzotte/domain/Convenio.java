package com.ramazzotte.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramazzotte.domain.dto.ConvenioDTO;
import com.ramazzotte.domain.dto.ConvenioNewDTO;
@Entity
public class Convenio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@OneToMany(mappedBy = "id.convenio")
	private Set<AtendimentoItens> itensAtendimento = new HashSet<>();
	
	private String descricao;
	private Boolean status = Boolean.TRUE;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Convenio(Integer id, String descricao, Boolean status) {
		this.id = id;
		this.descricao = descricao;
		this.status = status;
	}
	public Convenio() {
	}

	public Convenio(ConvenioNewDTO obj) {
		this.id = null;
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
	}
	public Convenio(ConvenioDTO obj) {
		this.id = null;
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
	}
	@Override
	public String toString() {
		return "Convenio [id=" + id + ", descricao=" + descricao + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convenio other = (Convenio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "convenio")
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
