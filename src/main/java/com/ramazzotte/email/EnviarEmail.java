package com.ramazzotte.email;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.dto.flat.EmailDTO;
import com.ramazzotte.email.EnvioEmailService.Mensagem;
@Service
public class EnviarEmail {
	
	@Autowired
	private EnvioEmailService envioEmail;


	@Async
	public void enviarEmailSenha(EmailDTO email) {
		Set<String> colecaoSetEmail = new HashSet<String>();
		colecaoSetEmail.add(email.getEmail());
		var mensagem = new  Mensagem(colecaoSetEmail, 
				" - Atenção para nova senha! ", 
				"senhaEmail.html");
		Map<String, Object> variaveisHtml = new HashMap<String, Object>();
		variaveisHtml.put("email", email);
		mensagem.setVariaveis(variaveisHtml);
		envioEmail.enviar(mensagem);
		
	}
	
	

}
