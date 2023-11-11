package com.ramazzotte.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Atendimento;
import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.Empresa;
import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.Patrimonio;
import com.ramazzotte.domain.Usuario;
import com.ramazzotte.security.DaringSecurity;
@Service
public class LogSistemaService {
    @Autowired
	private DaringSecurity daringSecurity;

	public LogSistema insert(Convenio obj, String acao) {
        String usuarioLogado = daringSecurity.getUsuario();
        
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}
	public LogSistema insert(Patrimonio obj, String acao) {
        String usuarioLogado = daringSecurity.getUsuario();        
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}
	
	public LogSistema insert(Atendimento obj, String acao) {
        String usuarioLogado = daringSecurity.getUsuario();
        
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}
	public LogSistema insert(Paciente obj, String acao) {
        String usuarioLogado = daringSecurity.getUsuario();        
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}
	
	public LogSistema insert(Exame obj, String acao) {
        String usuarioLogado = daringSecurity.getUsuario();        
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}

	public LogSistema insert(Usuario obj, String string) {
        String usuarioLogado = daringSecurity.getUsuario();
        
	    String comando = (string + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;	
	}
	public LogSistema insert(Empresa obj, String acao) {
		String usuarioLogado = daringSecurity.getUsuario();
	    String comando = (acao + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;
	}

}
