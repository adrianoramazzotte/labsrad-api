package com.ramazzotte.resource.excepition;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String mensagem;
	
	public FieldMessage() {
		
	}



	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}



	public String getMensagem() {
		return mensagem;
	}



	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	public FieldMessage(String fieldName, String mensagem) {
		this.fieldName = fieldName;
		this.mensagem = mensagem;
	}




	
	
}
