package com.ramazzotte.domain.dto.flat;

import java.util.HashSet;
import java.util.Set;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.validation.exame.ExameUpdate;
@ExameUpdate
public class ExameFlatUpdate {
	private Integer id;	
	private String descricao;
	private Boolean status;
	private Set<ExameFlatConvenio> examesconvenios = new HashSet<>();
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

	public ExameFlatUpdate(Integer id, String descricao, Boolean status) {
		this.id = id;
		this.descricao = descricao;
		this.status = status;

	}
	public ExameFlatUpdate() {
	}
	public ExameFlatUpdate(Exame obj) {
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
		conveniosexame(obj);
		
		
		
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((examesconvenios == null) ? 0 : examesconvenios.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ExameFlatUpdate other = (ExameFlatUpdate) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (examesconvenios == null) {
			if (other.examesconvenios != null)
				return false;
		} else if (!examesconvenios.equals(other.examesconvenios))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
