package com.ramazzotte.security.resource;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface Atendimento {

		@PreAuthorize("hasAuthority('C_ATEND')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar {}

		@PreAuthorize("hasAuthority('D_ATEND')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeExcluir {}
	
		@PreAuthorize("hasAuthority('U_ATEND')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAtualizar {}
		
		@PreAuthorize("hasAuthority('R_ATEND')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('U_ATEND')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarStatus {}

	}

	public @interface Convenio {

		@PreAuthorize("hasAuthority('C_CONV')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar {}

		@PreAuthorize("hasAuthority('D_CONV')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeExcluir {}
	
		@PreAuthorize("hasAuthority('U_CONV')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAtualizar {}
		
		@PreAuthorize("hasAuthority('R_CONV')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('S_CONV')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarStatus {}

	}
	
	public @interface Exame {

		@PreAuthorize("hasAuthority('C_EXME')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar {}

		@PreAuthorize("hasAuthority('D_EXME')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeExcluir {}
	
		@PreAuthorize("hasAuthority('U_EXME')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAtualizar {}
		
		@PreAuthorize("hasAuthority('R_EXME')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('S_EXME')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarStatus {}

	}
	
	public @interface Paciente {

		@PreAuthorize("hasAuthority('C_PCTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar {}

		@PreAuthorize("hasAuthority('D_PCTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeExcluir {}
	
		@PreAuthorize("hasAuthority('U_PCTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAtualizar {}
		
		@PreAuthorize("hasAuthority('R_PCTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('S_PCTE')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarStatus {}

	}
	
	public @interface Usuario {

		@PreAuthorize("hasAuthority('C_USU')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeCadastrar {}

		@PreAuthorize("hasAuthority('D_USU')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeExcluir {}
	
		@PreAuthorize("hasAuthority('U_USU')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAtualizar {}
		
		@PreAuthorize("hasAuthority('R_USU')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}
		
		@PreAuthorize("hasAuthority('S_USU')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarStatus {}

	}

	
}
