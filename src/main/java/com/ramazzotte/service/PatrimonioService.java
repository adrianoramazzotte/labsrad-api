package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.Patrimonio;
import com.ramazzotte.domain.dto.flat.PatrimonioFlat;
import com.ramazzotte.repository.LogSistemaRepository;
import com.ramazzotte.repository.PatrimonioRepository;
import com.ramazzotte.service.exception.DataIntegrityException;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.exception.ObjectNotFoundException;
import com.ramazzotte.service.util.Tenantuser;


@Service
public class PatrimonioService {
	@Autowired
	private LogSistemaService log;
	@Autowired
	private LogSistemaRepository repolog;
	@Autowired
	private Tenantuser tenantUsuario;

	@Autowired
	private PatrimonioRepository repo;

	public Patrimonio find(Integer id) {
		Optional<Patrimonio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Patrimonio.class.getName()));
	}
//	@Transactional
	public void delete(Integer id) {	
		find(id);
		try {			
			repo.deleteById(id);
			Patrimonio conv = new Patrimonio();
			conv.setId(id);
			logPatrimonio(conv, "excluir");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir convenio");
		}
	}

	@Transactional
	public Patrimonio insert(Patrimonio obj) {
		obj.setId(null);
		obj.setTenant(tenantUsuario.buscarOuFalhar());
		repo.save(obj);
		logPatrimonio(obj, "inserir");
		return obj;
	}
	

	private void logPatrimonio(Patrimonio obj, String string) {
		LogSistema logsistema = log.insert(obj, string);
		logsistema.setPatrimonio(obj);
		repolog.save(logsistema);
		
	}

	public List<PatrimonioFlat> findAllSQL() {
		List<PatrimonioFlat> convsFlat = new ArrayList<PatrimonioFlat>();
		List<Patrimonio> convs = repo.findAllSql(tenantUsuario.buscarOuFalharInt());
		for (Patrimonio conv :convs ) {
			PatrimonioFlat convFlat = new PatrimonioFlat(conv);  
		     convsFlat.add(convFlat);
		}
		return convsFlat;

	}
	
	public List<Patrimonio> findAllConv(int id) {
		return repo.findAllSqlConv(id);

	}
	public List<PatrimonioFlat> findAllSqlInativo() {
		List<PatrimonioFlat> convsFlat = new ArrayList<PatrimonioFlat>();
		List<Patrimonio> convs = repo.findAllSqlInativo(tenantUsuario.buscarOuFalharInt());
		for (Patrimonio conv :convs ) {
			PatrimonioFlat convFlat = new PatrimonioFlat(conv);  
		     convsFlat.add(convFlat);
		}
		return convsFlat;
	}




	public Patrimonio buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Patrimonio não encontrada", id)));
	}

	public Patrimonio from(Patrimonio atividadeNovo) {
		Patrimonio atividadeAtual = buscarOuFalhar(atividadeNovo.getId());
		BeanUtils.copyProperties(atividadeNovo, atividadeAtual, "id");
		atividadeAtual.setTenant(tenantUsuario.buscarOuFalhar());
		logPatrimonio(atividadeAtual, "alterar");
		return repo.save(atividadeAtual);
	}

	@Transactional
	public void status(Boolean obj, int id) {
		Patrimonio conv = buscarOuFalhar(id);
		conv.setStatus(obj);
		logPatrimonio(conv, "status");

	}

}
