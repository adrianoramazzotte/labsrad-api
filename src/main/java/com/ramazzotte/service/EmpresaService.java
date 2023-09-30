package com.ramazzotte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Empresa;
import com.ramazzotte.repository.EmpresaRepository;



@Service
public class EmpresaService {



	@Autowired
	private EmpresaRepository repo;


	public Empresa find(Integer id) {
		Empresa c = repo.buscarPorId(id);
		return c;
		
	}


	public List<Empresa> findAll() {
		List<Empresa> list = repo.buscarTodos();
		return list;
	}

}
