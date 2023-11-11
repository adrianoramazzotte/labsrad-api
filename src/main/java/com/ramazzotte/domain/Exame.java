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
import com.ramazzotte.domain.dto.flat.ExameFlatInsert;
@Entity
public class Exame implements Serializable{
	@Override
	public String toString() {
		return "Exame [id=" + id + ", descricao=" + descricao + ", status=" + status + ", itensAtendimento="
				+ itensAtendimento + ", examesConvenio=" + examesConvenio + ", logs=" + logs + "]";
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	private String descricao;
	@JsonIgnore
	private Boolean status;
	@OneToMany(mappedBy = "id.exame")
	private Set<AtendimentoItens> itensAtendimento = new HashSet<>();
//	@JsonIgnore
	@OneToMany(mappedBy = "id.exame")
	private Set<ExameConvenio> examesConvenio = new HashSet<>();
	
	public Exame() {
		super();
	}

	public Set<ExameConvenio> getExamesConvenio() {
		return examesConvenio;
	}

	public void setExamesConvenio(Set<ExameConvenio> examesConvenio) {
		this.examesConvenio = examesConvenio;
	}

	public Exame(Integer id, String descricao, Boolean status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.status = status;
	}

	public Exame(ExameFlatInsert obj) {
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
				
	}

	public Exame(Integer id, String descricao, Boolean status, Set<AtendimentoItens> itensAtendimento,
			Set<ExameConvenio> examesConvenio) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.status = status;
		this.itensAtendimento = itensAtendimento;
		this.examesConvenio = examesConvenio;
	}

	public Set<AtendimentoItens> getItensAtendimento() {
		return itensAtendimento;
	}

	public void setItensAtendimento(Set<AtendimentoItens> itensAtendimento) {
		this.itensAtendimento = itensAtendimento;
	}

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

	public void addItem(ExameConvenio exameconvenio) {
		
		examesConvenio.add(exameconvenio);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Exame other = (Exame) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "exame")
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
