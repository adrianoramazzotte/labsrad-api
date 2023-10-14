package com.ramazzotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.dto.ConvenioDTO;
import com.ramazzotte.domain.dto.ConvenioNewDTO;
import com.ramazzotte.repository.ConvenioRepository;



@Service
public class ConvenioService {



	@Autowired
	private ConvenioRepository repo;


	public Convenio find(Integer id) {
		Convenio c = repo.buscarPorId(id);
		return c;
		
	}


	public List<Convenio> findAll() {
		List<Convenio> list = repo.buscarTodos();
		return list;
	}


	public Convenio insert(ConvenioNewDTO obj) {
		Convenio conv = new  Convenio(obj);
		
		return repo.save(conv);
	}


	public Convenio atualizaConvenio(ConvenioDTO obj) {
		Convenio conv = new  Convenio(obj);
		return repo.save(conv);
	}


	public void delete(Integer id) {
		repo.deleteById(id);
		
	}

}
