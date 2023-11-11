package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Classepermissao;
import com.ramazzotte.domain.Usuario;
import com.ramazzotte.domain.dto.flat.ClassePermissaoFlat;
import com.ramazzotte.repository.ClassepermissaoRepository;
import com.ramazzotte.repository.UsuarioRepository;
import com.ramazzotte.service.exception.DataIntegrityException;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.exception.ObjectNotFoundException;


@Service
public class ClassepermissaoService {

 	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ClassepermissaoRepository repo;


	public Classepermissao find(Integer id) {
		Optional<Classepermissao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Classepermissao.class.getName()));
	}

	public void delete(Integer id) {	
		find(id);
		try {			
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir permissao");
		}
	}

	@Transactional
	public Classepermissao insert(Classepermissao obj) {
		obj.setId(null);
		repo.save(obj);
	//	logConvenio(obj, "inserir");
		return obj;
	}
	

	public List<ClassePermissaoFlat> findAllSQL(int id) {
		Usuario usuario = usuarioRepository.findPorId(id);
		List<Classepermissao>classePermissoes = repo.findAllSql();		
		List<ClassePermissaoFlat> permissoesFlat = new ArrayList<>();		
		for (Classepermissao obj : classePermissoes) {
			ClassePermissaoFlat permissaoFlat = new ClassePermissaoFlat(obj, usuario.getPermissoes());
			permissoesFlat.add(permissaoFlat);
		}

		return permissoesFlat;
	}	
		
		
	public List<Classepermissao> findAllSqlInativo() {
		return repo.findAllSqlInativo();
	}


	public Classepermissao buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Classepermissao não encontrada", id)));
	}

	public Classepermissao from(Classepermissao atividadeNovo) {
		Classepermissao atividadeAtual = buscarOuFalhar(atividadeNovo.getId());
		BeanUtils.copyProperties(atividadeNovo, atividadeAtual, "id");
	
		return repo.save(atividadeAtual);
	}


}
