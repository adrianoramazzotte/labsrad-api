package com.ramazzotte.domain.dto.flat;

import java.io.Serializable;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramazzotte.domain.Permissao;

public class PermissoesFlat implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	private Integer id;
	private Boolean read;
	private Boolean create;
	private Boolean update;
	private Boolean delete;
	private Boolean status;
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public Boolean getCreate() {
		return create;
	}
	public void setCreate(Boolean create) {
		this.create = create;
	}
	public Boolean getUpdate() {
		return update;
	}
	public void setUpdate(Boolean update) {
		this.update = update;
	}
	public Boolean getDelete() {
		return delete;
	}
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public PermissoesFlat() {
	}
	public PermissoesFlat(Permissao obj) {		
		this.read = true;
		this.create = true;
		this.update = true;
		this.delete = true;
		this.status = true;
		
	}
	public PermissoesFlat(Boolean create, Boolean update, Boolean delete, Boolean read, Boolean status) {
		this.read = read;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.status = status;
	}
	public PermissoesFlat(Boolean read) {
		this.read = read;
	}
	@Override
	public String toString() {
		return "PermissoesFlat [id=" + id + ", read=" + read + ", create=" + create + ", update=" + update + ", delete="
				+ delete + ", status=" + status + "]";
	}
	

}
