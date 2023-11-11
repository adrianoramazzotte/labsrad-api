package com.ramazzotte.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Usuario;
import com.ramazzotte.domain.dto.flat.EmailDTO;
import com.ramazzotte.repository.UsuarioRepository;
import com.ramazzotte.service.exception.ObjectNotFoundException;

@Service
public class ResetSenhaService {

	@Autowired
	private UsuarioRepository usuRepo;
	@Autowired
	private PasswordEncoder pe;
	

	
	private Random rand = new Random();
	
	public void sendNewPassword(EmailDTO email) {
		
		Usuario usuario = usuRepo.buscarUsuario(email.getEmail());
		if (usuario == null) {
			throw new ObjectNotFoundException("Email inválido ou inexistente");
		}
		
		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));
		
		usuRepo.save(usuario);
		email.setSenha(newPass);


	}
	


	private String newPassword() {
		char[] vet = new char[8];
		for (int i=0; i<8; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
