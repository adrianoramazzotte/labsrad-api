package com.ramazzotte.domain.dto;

public class Permission {

	
	 @Override
	public String toString() {
		return "Permission [read=" + read + ", create=" + create + ", update=" + update + ", delete=" + delete
				+ ", status=" + status + "]";
	}
	private String read;
	 private String create;
	 private String update;
	 private String delete;
	 private String status;
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getCreate() {
		return create;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Permission(String read, String create, String update, String delete, String status) {

		this.read = read;
		this.create = create;
		this.update = update;
		this.delete = delete;
		this.status = status;
	}
	public Permission() {

	}
	 
	 
}
