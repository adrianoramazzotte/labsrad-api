package com.ramazzotte.domain.dto.flat;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.validation.exame.ExameInsert;
@ExameInsert
public class ExameFlatInsert {

	private Integer id;	
	private String descricao;
	private Boolean status;
	private OffsetDateTime datagravacao;
	private String emailusuario;
	private Set<ExameFlatConvenio> examesconvenios = new HashSet<>();
	public Integer getId() {
		return id;
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

	public ExameFlatInsert(Integer id, String descricao, Boolean status) {
		this.id = id;
		this.descricao = descricao;
		this.status = status;
		

	}
	public ExameFlatInsert() {
	}
	public ExameFlatInsert(Exame obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
		this.datagravacao = obj.getLogs().getDatagravacao();
		this.emailusuario = obj.getLogs().getEmailUsuario();
		conveniosexame(obj);
		
		
		
		
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
		ExameFlatInsert other = (ExameFlatInsert) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	public Set<ExameFlatConvenio> getExamesconvenios() {
		return examesconvenios;
	}
	public void setExamesconvenios(Set<ExameFlatConvenio> examesconvenios) {
		this.examesconvenios = examesconvenios;
	}
	private void conveniosexame(Exame obj) {
		for (ExameConvenio conveniosExames : obj.getExamesConvenio()) {
			ExameFlatConvenio convenioExames = new ExameFlatConvenio(conveniosExames);
			examesconvenios.add(convenioExames);
		}
	}
	@Override
	public String toString() {
		return "ExameFlat [id=" + id + ", descricao=" + descricao + ", status=" + status + ", examesConvenios="
				+ examesconvenios + "]";
	}
	

}
