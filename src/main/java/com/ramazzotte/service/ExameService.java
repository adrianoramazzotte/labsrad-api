package com.ramazzotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.repository.ExameConvenioRepository;

@Service
public class ExameService {
    @Autowired
	private ExameConvenioRepository repo;
    
    
	public List<ExameConvenio> findAll() {
		List<ExameConvenio> lista  = repo.findAll();
		return lista;
	}

}
