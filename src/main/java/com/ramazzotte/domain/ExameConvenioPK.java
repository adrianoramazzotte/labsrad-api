package com.ramazzotte.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class ExameConvenioPK implements Serializable{
	@Override
	public String toString() {
		return "ExameConvenioPK [exame=" + exame + ", convenio=" + convenio + "]";
	}

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="exame_id")
	private Exame exame;
	
	@ManyToOne
	@JoinColumn(name="convenio_id")
	private Convenio convenio;

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public ExameConvenioPK(Exame exame, Convenio convenio) {
		this.exame = exame;
		this.convenio = convenio;
	}

	public ExameConvenioPK() {
	}
	
	

}





