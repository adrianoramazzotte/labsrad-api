package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.domain.ExameConvenioPK;
import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.dto.flat.ExameFlatConvenio;
import com.ramazzotte.domain.dto.flat.ExameFlatInsert;
import com.ramazzotte.domain.dto.flat.ExameFlatUpdate;
import com.ramazzotte.repository.ConvenioRepository;
import com.ramazzotte.repository.ExameConvenioRepository;
import com.ramazzotte.repository.ExameRepository;
import com.ramazzotte.repository.LogSistemaRepository;
import com.ramazzotte.service.exception.DataIntegrityException;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.util.Tenantuser;


@Service
public class ExameService {
	
	@Autowired
	private LogSistemaService log;
	@Autowired
	private LogSistemaRepository repolog;
	@Autowired
	private ConvenioRepository repoconvenio;
	
	@Autowired
	private ExameConvenioRepository repoexameconvenio;
	@Autowired
	private Tenantuser tenantUsuario;
	@Autowired
	private ExameRepository repo;

	public ExameFlatInsert find(Integer id) {

		Exame obj = repo.findPorId(id);

		ExameFlatInsert ef = new ExameFlatInsert(obj);

		return ef;
	}
//	@Transactional
	public void delete(Integer id) {	
		find(id);
		try {			
			repo.deleteById(id);
			Exame ex = new Exame();
			ex.setId(id);
			logExame(ex, "excluir");	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir Exame");
		}
	}

	@Transactional
	public Exame insert(ExameFlatInsert novoFlat, ExameFlatInsert obj) {
		novoFlat.setId(null);
		Exame exame= new Exame(novoFlat);
		exame.setStatus(true);
		exame.setDescricao(obj.getDescricao());
		exame.setTenant(tenantUsuario.buscarOuFalhar());
		repo.save(exame);
		for (ExameFlatConvenio ipp : novoFlat.getExamesconvenios()) {
			ExameConvenioPK exaChave = new ExameConvenioPK();
			exaChave.setExame(exame);
			exaChave.setConvenio(repoconvenio.findByCodigo(ipp.getIdconvenio()));
			ExameConvenio exameconvenio = new ExameConvenio();
			exameconvenio.setPreco(ipp.getPreco());
			exameconvenio.setStatus(true);
			exameconvenio.setId(exaChave);
			exameconvenio.setTenant(tenantUsuario.buscarOuFalhar());
			repoexameconvenio.save(exameconvenio);
			exame.addItem(exameconvenio);
			}
		    logExame(exame, "inserir");		    
			return repo.save(exame);
	}
	
	


    private void logExame(Exame obj, String string) {
	   LogSistema logsistema = log.insert(obj, string);
	   logsistema.setExame(obj);
	   repolog.save(logsistema);	
   }


	public List<ExameFlatInsert> findAllSQL() {
		List<Exame> exames = repo.findAllSql(tenantUsuario.buscarOuFalharInt());
		List<ExameFlatInsert> examesflat = new ArrayList<>();
		for (Exame obj : exames) {
			ExameFlatInsert exameflat = new ExameFlatInsert(obj);
			examesflat.add(exameflat);
		}
		return examesflat;

	}
	
	public List<ExameFlatInsert> findPorConvenio(int idConvenio) {
		List<Exame> exames = repo.findAllPorConvenio(idConvenio);
		List<ExameFlatInsert> examesflat = new ArrayList<>();
		for (Exame obj : exames) {
			ExameFlatInsert exameflat = new ExameFlatInsert(obj);
			examesflat.add(exameflat);
		}
		return examesflat;

	}
	public List<ExameFlatInsert> findAllSqlInativo() {
		List<Exame> exames = repo.findAllSqlInativo();
		List<ExameFlatInsert> examesflat = new ArrayList<>();
		for (Exame obj : exames) {
			ExameFlatInsert exameflat = new ExameFlatInsert(obj);
			examesflat.add(exameflat);
		}
		return examesflat;
	}




	public Exame buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Exame não encontrada", id)));
	}

	

	@Transactional
	public void status(Boolean obj, int id) {
		Exame exame = buscarOuFalhar(id);
		exame.setStatus(obj);
		logExame(exame, "status");

	}
	@Transactional
	public ExameFlatUpdate from(ExameFlatUpdate novoobj) {
		Exame exame = new Exame();
		exame.setId(novoobj.getId());
		exame.setStatus(novoobj.getStatus());
		exame.setDescricao(novoobj.getDescricao());
		exame.setTenant(tenantUsuario.buscarOuFalhar());
		repoexameconvenio.deleteByIdExame(exame.getId());
		for (ExameFlatConvenio ipp : novoobj.getExamesconvenios()) {
			ExameConvenioPK exaChave = new ExameConvenioPK();
			exaChave.setExame(exame);
			exaChave.setConvenio(repoconvenio.findByCodigo(ipp.getIdconvenio()));
			ExameConvenio exameconvenio = new ExameConvenio();
			exameconvenio.setPreco(ipp.getPreco());
			exameconvenio.setStatus(true);
			exameconvenio.setId(exaChave);
			exameconvenio.setTenant(tenantUsuario.buscarOuFalhar());
			repoexameconvenio.save(exameconvenio);
			exame.addItem(exameconvenio);
			}  	
		   repo.save(exame);	
		   logExame(exame, "alterar");
			return novoobj;
	}

}
