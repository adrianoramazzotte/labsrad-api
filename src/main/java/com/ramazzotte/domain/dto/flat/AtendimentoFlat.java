package com.ramazzotte.domain.dto.flat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.domain.AtendimentoItens;
import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.Tenant;
import com.ramazzotte.domain.Usuario;

public class AtendimentoFlat implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer idficha;
	private Boolean status = Boolean.TRUE;	
	private Paciente paciente;
	private String nome;
	private Date datanasc;
	private String sexo;

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
//   @JsonIgnore
//	public Set<AtendimentoItensFlat> getItensatendimentoFlat() {
//		return itensatendimentoflat;
//	}
//   @JsonIgnore
//	public void setItensatendimentoflat(Set<AtendimentoItensFlat> itensatendimentoflat) {
//		this.itensatendimentoflat = itensatendimentoflat;
//	}
  
	private String formapagamento;	
	private OffsetDateTime datagravacao;
	private String emailusuario;
	private Set<AtendimentoItensFlat> itensatendimentoflat = new HashSet<>();
	private BigDecimal total;
	private BigDecimal desconto;
	
	private Tenant tenant;
	
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public AtendimentoFlat(Integer id, Integer idficha, Boolean status, Paciente paciente, String formapagamento,
			OffsetDateTime datagravacao, String emailusuario, Set<AtendimentoItensFlat> itensatendimentoflat, BigDecimal total,
			BigDecimal desconto, Date datalancamento, Usuario usuario) {
		super();
		this.id = id;
		this.idficha = idficha;
		this.status = status;
		this.paciente = paciente;
		this.formapagamento = formapagamento;
		this.datagravacao = datagravacao;
		this.emailusuario = emailusuario;
		this.itensatendimentoflat = itensatendimentoflat;
		this.total = total;
		this.desconto = desconto;
		this.datalancamento = datalancamento;
		this.usuario = usuario;
	}



	public OffsetDateTime getDatagravacao() {
		return datagravacao;
	}

	public void setDatagravacao(OffsetDateTime datagravacao) {
		this.datagravacao = datagravacao;
	}

	public String getEmailusuario() {
		return emailusuario;
	}

	public void setEmailusuario(String emailusuario) {
		this.emailusuario = emailusuario;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	private Date datalancamento;
	@JsonIgnoreProperties({ "logs", "permissoes" })
	private Usuario usuario;
	


	public String getFormapagamento() {
		return formapagamento;
	}

	public void setFormaPagamento(String formapagamento) {
		this.formapagamento = formapagamento;
	}

	public AtendimentoFlat(Atendimento obj) {
		super();
		this.id = obj.getId();
		this.idficha = obj.getIdFicha();
		this.status = obj.getStatus();
		this.paciente = obj.getPaciente();
		this.nome = obj.getPaciente().getNome();
		this.datanasc = obj.getPaciente().getDatanasc();
		this.sexo = obj.getPaciente().getSexo();
		this.total = obj.getTotal();
		this.datalancamento = obj.getDatalancamento();
		this.usuario = obj.getUsuario();
		this.formapagamento = obj.getFormapagamento();
		this.datagravacao = obj.getLogs().getDatagravacao();
		this.emailusuario = obj.getLogs().getEmailUsuario();
		this.tenant = obj.getTenant();
		itensAtendimentoFlat(obj);
		
	}
	public AtendimentoFlat(Atendimento obj, String filtro) {
		super();
		this.id = obj.getId();
		this.idficha = obj.getIdFicha();
		this.status = obj.getStatus();
		//this.paciente = obj.getPaciente();
		this.nome = obj.getPaciente().getNome();
		this.datanasc = obj.getPaciente().getDatanasc();
		this.sexo = obj.getPaciente().getSexo();
		this.total = obj.getTotal();
		this.datalancamento = obj.getDatalancamento();
		//this.usuario = obj.getUsuario();
		this.formapagamento = obj.getFormapagamento();
		this.datagravacao = obj.getLogs().getDatagravacao();
		this.emailusuario = obj.getLogs().getEmailUsuario();
		//this.tenant = obj.getTenant();
		//itensAtendimentoFlat(obj);
		
	}

	private void itensAtendimentoFlat(Atendimento obj) {
		for (AtendimentoItens atenditens : obj.getItensAtendimento()) {
			
			AtendimentoItensFlat atendimentoFlat = new AtendimentoItensFlat(atenditens);
			itensatendimentoflat.add(atendimentoFlat);	
			
		}
	}
	

	public AtendimentoFlat() {
	}

	public AtendimentoFlat(Integer id, Integer idFicha, Boolean status, Paciente paciente,
			Set<AtendimentoItensFlat> itensatendimentoflat, BigDecimal total, Date datalancamento,
			Usuario usuario) {
		this.id = id;
		this.idficha = idficha;
		this.status = status;
		this.paciente = paciente;
		this.itensatendimentoflat = itensatendimentoflat;
		this.total = total;
		this.datalancamento = datalancamento;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIdficha() {
		return idficha;
	}

	public void setIdficha(Integer idficha) {
		this.idficha = idficha;
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
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public Set<AtendimentoItensFlat> getItensatendimento() {
		return itensatendimentoflat;
	}

	public void setItensatendimento(Set<AtendimentoItensFlat> itensatendimentoflat) {
		this.itensatendimentoflat = itensatendimentoflat;
	}

	@Override
	public String toString() {
		return "AtendimentoFlat [id=" + id + ", idFicha=" + idficha + ", status=" + status + ", paciente=" + paciente
				+ ", itensAtendimentoFlat=" + itensatendimentoflat + ", total=" + total + ", dataLancamento="
				+ datalancamento + ", usuario=" + usuario + "]";
	}
	

}
