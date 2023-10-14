package com.ramazzotte.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.repository.filter.PacienteFilter;


public interface PacienteRepositoryQuery {
	
	public Page<Paciente> filtrar(PacienteFilter Pacientefilter, Pageable pageable);

}
