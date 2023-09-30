package com.ramazzotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Convenio;
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


	public Convenio insert(Convenio obj) {
		return repo.save(obj);
	}


	public Convenio atualizaConvenio(Convenio obj) {

		return repo.save(obj);
	}


	public void delete(Integer id) {
		repo.deleteById(id);
		
	}

}
