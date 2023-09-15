package com.ramazzotte.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Embeddable
public class AtendimentoItensPK implements Serializable{
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="exame_id")
	private Exame exame;
	
	private int codigo;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@ManyToOne
	@JoinColumn(name="convenio_id")
	private Convenio convenio;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="atendimento_id")
	private Atendimento atendimento;
	
	
	public Convenio getConvenio() {
		return convenio;
	}
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	public AtendimentoItensPK() {
	
	}
	public AtendimentoItensPK(Exame exame, Convenio convenio, Atendimento atendimento, int codigo) {
		this.exame = exame;
		this.convenio = convenio;
		this.atendimento = atendimento;
		this.codigo = codigo;
	}
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public Atendimento getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
//	@Override
//	public String toString() {
//		return "AtendimentoItensPK [exame=" + exame + ", convenio=" + convenio + ", atendimento=" + atendimento + "]";
//	}
	

}
