package com.ramazzotte.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Atendimento implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private Integer idFicha;
	
	private String formapagamento;	
	
	private Boolean status = Boolean.TRUE;
	@ManyToOne
	private Paciente paciente;
	private BigDecimal valortotal;	
	private BigDecimal desconto;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @Column(columnDefinition = "date")    
	@JsonFormat(pattern= "yyyy/MM/dd HH:mm:ss:SSS")
    private Date datalancamento;	
	@JsonIgnoreProperties({ "logs", "permissoes", "senha" })
    @ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "id.atendimento", cascade = CascadeType.REMOVE)
	private Set<AtendimentoItens> itensAtendimento = new HashSet<>();
	public Integer getId() {
		return id;
	}
	@ManyToOne
	private Tenant tenant;
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	
	
	
	
	public Atendimento() {
		super();
	}

	public Atendimento(Integer id, Integer idFicha, Boolean status, Paciente paciente, BigDecimal valortotal,
			BigDecimal desconto, Date datalancamento, Usuario usuario, Set<AtendimentoItens> itensAtendimento) {
		super();
		this.id = id;
		this.idFicha = idFicha;
		this.status = status;
		this.paciente = paciente;
		this.valortotal = valortotal;
		this.desconto = desconto;
		this.datalancamento = datalancamento;
		this.usuario = usuario;
		this.itensAtendimento = itensAtendimento;
	}

	
	
	public String getFormapagamento() {
		return formapagamento;
	}

	public void setFormapagamento(String formapagamento) {
		this.formapagamento = formapagamento;
	}

	public BigDecimal getValortotal() {
		return valortotal;
	}

	public void setValortotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}


		
	public Integer getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(Integer idFicha) {
		this.idFicha = idFicha;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	public BigDecimal getTotal() {
		return valortotal;
	}
	public void setTotal(BigDecimal valortotal) {
		this.valortotal = valortotal;
	}
	
	public Date getDatalancamento() {
		return datalancamento;
	}
	public void setDatalancamento(Date datalancamento) {
		this.datalancamento = datalancamento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<AtendimentoItens> getItensAtendimento() {
		return itensAtendimento;
	}

	public void setItensAtendimento(Set<AtendimentoItens> itensAtendimento) {
		this.itensAtendimento = itensAtendimento;
	}

	public void addItens(AtendimentoItens atenditens) {
		itensAtendimento.add(atenditens);
		
	}
	
	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", idFicha=" + idFicha + ", status=" + status + ", paciente=" + paciente
				+ ", valorTotal=" + valortotal + ", desconto=" + desconto + ", dataLancamento=" + datalancamento
				+ ", usuario=" + usuario + ", itensAtendimento=" + itensAtendimento + "]";
	}
	@JsonIgnore
	@OneToMany(mappedBy = "atendimento")
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

	
}
