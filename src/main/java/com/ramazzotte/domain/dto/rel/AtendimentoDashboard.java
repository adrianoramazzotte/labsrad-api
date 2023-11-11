package com.ramazzotte.domain.dto.rel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.ramazzotte.domain.AtendimentoItens;

public class AtendimentoDashboard {
	private Integer prontuario;
	private Integer atendimento;
	private String paciente;
	private String exame;
	private String convenio;
	private Date dataLancamento;
	private BigDecimal preco;
	private BigDecimal desconto;
	private BigDecimal total;
	private String usuario;
	private Integer acesso;
	private Integer QE;
	
	
	
	
	public Integer getQE() {
		return QE;
	}

	public void setQE(Integer qE) {
		QE = qE;
	}

	public Integer getAcesso() {
		return acesso;
	}

	public void setAcesso(Integer acesso) {
		this.acesso = acesso;
	}

	@Override
	public String toString() {
		return "AtendimentoRelPorData [Prontuario=" + prontuario + ", Atendimento=" + atendimento + ", Paciente="
				+ paciente + ", Exame=" + exame + ", Convenio=" + convenio + ", DataLancamento=" + dataLancamento
				+ ", preco=" + preco + ", desconto=" + desconto + ", total=" + total + ", Usuario=" + usuario + "]";
	}

	public Integer getProntuario() {
		return this.prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public Integer getAtendimento() {
		return atendimento;
	}
	public void setAtendimento(Integer atendimento) {
		this.atendimento = atendimento;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getExame() {
		return exame;
	}
	public void setExame(String exame) {
		this.exame = exame;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getUsuario() {
		return usuario;
	}

	public AtendimentoDashboard(Integer Prontuario, Integer atendimento, String paciente, String exame,
			String convenio, Date dataLancamento, BigDecimal preco, BigDecimal desconto, BigDecimal total,
			String usuario, Integer acesso) {
		super();
		this.prontuario = Prontuario;
		this.atendimento = atendimento;
		this.paciente = paciente;
		this.exame = exame;
		this.convenio = convenio;
		this.dataLancamento = dataLancamento;
		this.preco = preco;
		this.desconto = desconto;
		this.total = total;
		this.usuario = usuario;
		this.acesso = acesso;
	}
	public AtendimentoDashboard() {
	}
	public AtendimentoDashboard(AtendimentoItens obj) {
		this.prontuario = obj.getId().getAtendimento().getIdFicha();
		this.atendimento = obj.getId().getAtendimento().getId();
		this.paciente = obj.getId().getAtendimento().getPaciente().getNome();
		this.exame = obj.getId().getExame().getDescricao();
		this.convenio = obj.getId().getConvenio().getDescricao();
		this.dataLancamento = obj.getId().getAtendimento().getDatalancamento();
		this.preco = obj.getPreco();
		this.desconto = obj.getDesconto();
		this.total = obj.getTotal();
		this.usuario = obj.getId().getAtendimento().getUsuario().getNome();
		this.acesso = obj.getAcesso();
	}
	
	public Date localDataToData(LocalDate ld) {
		Date data = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return data;
	}
	

}
