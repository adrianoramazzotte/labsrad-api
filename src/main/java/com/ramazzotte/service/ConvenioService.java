package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.dto.flat.ConvenioFlat;
import com.ramazzotte.domain.dto.flat.ExameConvenioItensFlat2;
import com.ramazzotte.repository.ConvenioRepository;
import com.ramazzotte.repository.ExameRepository;
import com.ramazzotte.repository.LogSistemaRepository;
import com.ramazzotte.service.exception.DataIntegrityException;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.exception.ObjectNotFoundException;
import com.ramazzotte.service.util.Tenantuser;


@Service
public class ConvenioService {
	@Autowired
	private LogSistemaService log;
	@Autowired
	private LogSistemaRepository repolog;
	@Autowired
	private Tenantuser tenantUsuario;

	@Autowired
	private ConvenioRepository repo;
	@Autowired
	private ExameRepository repoExame;

	public Convenio find(Integer id) {
		Optional<Convenio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Convenio.class.getName()));
	}
//	@Transactional
	public void delete(Integer id) {	
		find(id);
		try {			
			repo.deleteById(id);
			Convenio conv = new Convenio();
			conv.setId(id);
			logConvenio(conv, "excluir");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir convenio");
		}
	}

	@Transactional
	public Convenio insert(Convenio obj) {
		obj.setId(null);
		obj.setTenant(tenantUsuario.buscarOuFalhar());
		repo.save(obj);
		logConvenio(obj, "inserir");
		return obj;
	}
	

	private void logConvenio(Convenio obj, String string) {
		LogSistema logsistema = log.insert(obj, string);
		logsistema.setConvenio(obj);
		repolog.save(logsistema);
		
	}

	public List<ConvenioFlat> findAllSQL() {
		List<ConvenioFlat> convsFlat = new ArrayList<ConvenioFlat>();
		List<Convenio> convs = repo.findAllSql(tenantUsuario.buscarOuFalharInt());
		for (Convenio conv :convs ) {
		     ConvenioFlat convFlat = new ConvenioFlat(conv);  
		     convsFlat.add(convFlat);
		}
		return convsFlat;

	}
	
	public List<Convenio> findAllConv(int id) {
		return repo.findAllSqlConv(id);

	}
	public List<ConvenioFlat> findAllSqlInativo() {
		List<ConvenioFlat> convsFlat = new ArrayList<ConvenioFlat>();
		List<Convenio> convs = repo.findAllSqlInativo(tenantUsuario.buscarOuFalharInt());
		for (Convenio conv :convs ) {
		     ConvenioFlat convFlat = new ConvenioFlat(conv);  
		     convsFlat.add(convFlat);
		}
		return convsFlat;

	}




	public Convenio buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Convenio não encontrada", id)));
	}

	public Convenio from(Convenio atividadeNovo) {
		Convenio atividadeAtual = buscarOuFalhar(atividadeNovo.getId());
		atividadeAtual.setTenant(tenantUsuario.buscarOuFalhar());
		
		BeanUtils.copyProperties(atividadeNovo, atividadeAtual, "id");
		atividadeAtual.setTenant(tenantUsuario.buscarOuFalhar());
		logConvenio(atividadeAtual, "alterar");
	
		return repo.save(atividadeAtual);
	}

	@Transactional
	public void status(Boolean obj, int id) {
		Convenio conv = buscarOuFalhar(id);
		conv.setStatus(obj);
		conv.setTenant(tenantUsuario.buscarOuFalhar());
		logConvenio(conv, "status");

	}
	public List<ExameConvenioItensFlat2> findAllSQL(Integer idexame) {
		List<ExameConvenioItensFlat2> convsFlat = new ArrayList<ExameConvenioItensFlat2>();
		Exame convs = repoExame.findByCodigo( idexame);
		for (ExameConvenio conv :convs.getExamesConvenio()) {
			ExameConvenioItensFlat2 convFlat = new ExameConvenioItensFlat2(conv);  
		     convsFlat.add(convFlat);
		}
		return convsFlat;

	}

}
