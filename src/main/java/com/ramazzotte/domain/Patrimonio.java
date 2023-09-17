package com.ramazzotte.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Patrimonio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String descricao;
	private OffsetDateTime dataaquisicao;
	private BigDecimal valor;
	private Boolean status = Boolean.TRUE;
	@ManyToOne
	private Empresa empresa;
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
	public OffsetDateTime getDataaquisicao() {
		return dataaquisicao;
	}
	public void setDataaquisicao(OffsetDateTime dataaquisicao) {
		this.dataaquisicao = dataaquisicao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Patrimonio(Integer id, String descricao, OffsetDateTime dataaquisicao, BigDecimal valor, Boolean status,
			Empresa empresa) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataaquisicao = dataaquisicao;
		this.valor = valor;
		this.status = status;
		this.empresa = empresa;
	}
	public Patrimonio() {
	}

	public Patrimonio(@Valid Patrimonio obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.dataaquisicao = obj.getDataaquisicao();
		this.valor = obj.getValor();
		this.status = obj.getStatus();
		this.empresa = obj.getEmpresa();
	}

	@JsonIgnore
	@OneToMany(mappedBy = "patrimonio")
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
