package com.ramazzotte.email;

import java.util.Map;
import java.util.Set;



public interface EnvioEmailService {
	
    void enviar(Mensagem mensagem);
	

	class Mensagem {
		
		public Mensagem(Set<String> destinatarios, String assunto, String corpo) {
			super();
			this.destinatarios = destinatarios;
			this.assunto = assunto;
			this.corpo = corpo;
		}
		private Set<String> destinatarios;
		private String assunto;
		public Set<String> getDestinatarios() {
			return destinatarios;
		}
		public void setDestinatarios(Set<String> destinatarios) {
			this.destinatarios = destinatarios;
		}
		
		public void addDestinatarios(String destinatario) {
			this.destinatarios.add(destinatario);
		}
		
		public String getCorpo() {
			return corpo;
		}
		public void setCorpo(String corpo) {
			this.corpo = corpo;
		}
		private String corpo;
		public String getAssunto() {
			return assunto;
		}
		public void setAssunto(String assunto) {
			this.assunto = assunto;
		}
		@Override
		public String toString() {
			return "Mensagem [destinatarios=" + destinatarios + ", assunto=" + assunto + ", corpo=" + corpo + "]";
		}
	
		private Map<String, Object> variaveis;
		public Map<String, Object> getVariaveis() {
			return variaveis;
		}
		public void setVariaveis(Map<String, Object> variaveis) {
			this.variaveis = variaveis;
		}
		
		
		
	}

	
	
}
