package com.ramazzotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.repository.ExameConvenioRepository;
import com.ramazzotte.repository.ExameRepository;

@Service
public class ExameService {
    @Autowired
	private ExameConvenioRepository repo;
    @Autowired
    private ExameRepository repoExame;
    
    
	public List<ExameConvenio> findAll() {
		List<ExameConvenio> lista  = repo.findAll();
		return lista;
	}


	public List<Exame> findAllSQL() {
		List<Exame> lista = repoExame.findAll();
		return lista;
	}


	public Exame find(Integer id ) {
		Exame exame = repoExame.findPorID(id);
		return exame;
	}


	public void delete(Integer id) {
		repoExame.deleteById(id);
		
	}


	public Exame insert(Exame obj) {
		return repoExame.save(obj);
	}


	public Exame from(Exame obj) {
		Exame exameBanco = repoExame.findPorID(obj.getId());
		exameBanco.setId(obj.getId());
		exameBanco.setDescricao(null);
		exameBanco.setStatus(null);
		return repoExame.save(exameBanco);
	}

}
