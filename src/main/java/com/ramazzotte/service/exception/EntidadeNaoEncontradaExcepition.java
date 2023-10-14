package com.ramazzotte.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaExcepition extends RuntimeException {
   private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaExcepition(String mensagem) {
		super(mensagem);		
	}
}