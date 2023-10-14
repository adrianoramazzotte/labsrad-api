package com.ramazzotte.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.repository.PacienteRepository;
import com.ramazzotte.repository.filter.PacienteFilter;

public class PacienteResource {
	
	@Autowired
	private PacienteRepository repo;
	
	@RequestMapping(value = "/filtro",method = RequestMethod.GET)
	public ResponseEntity<?> findAll(PacienteFilter pacienteFilter, Pageable pageable) {
		Page<Paciente> pac = repo.filtrar(pacienteFilter, pageable);
		return ResponseEntity.ok().body(pac);
	}

}
