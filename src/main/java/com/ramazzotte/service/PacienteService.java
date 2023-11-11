package com.ramazzotte.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ramazzotte.domain.LogSistema;
import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.dto.flat.PacienteFlat;
import com.ramazzotte.repository.LogSistemaRepository;
import com.ramazzotte.repository.PacienteRepository;
import com.ramazzotte.service.exception.DataIntegrityException;
import com.ramazzotte.service.exception.EntidadeNaoEncontradaExcepition;
import com.ramazzotte.service.exception.ObjectNotFoundException;
import com.ramazzotte.service.util.Tenantuser;


@Service
public class PacienteService {

	@Autowired
	private LogSistemaService log;
	@Autowired
	private LogSistemaRepository repolog;
	@Autowired
	private PacienteRepository repo;
	@Autowired
	private Tenantuser tenantUsuario;

	public Paciente find(Integer id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}

	public void delete(Integer id) {	
		find(id);
		try {			
			repo.deleteById(id);
			Paciente pac = new Paciente();
			pac.setId(id);
			logPaciente(pac, "excluir");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir paciente com referência em outros registros");
		}

		
	}
	
	
	@Transactional
	public Paciente insert(Paciente obj) {
		obj.setId(null);
		obj.setTenant(tenantUsuario.buscarOuFalhar());
		repo.save(obj);
		logPaciente(obj, "inserir");
		return obj;
	}




	private void logPaciente(Paciente obj, String string) {
		LogSistema logsistema = log.insert(obj, string);
		logsistema.setPaciente(obj);
		repolog.save(logsistema);

}


	
	public List<PacienteFlat> findAllSQL() {
		List<PacienteFlat>pacsFlat = new ArrayList<PacienteFlat>();
		List<Paciente>pacs = repo.findAllSql(tenantUsuario.buscarOuFalharInt());
        for(Paciente pac: pacs) {
        	String ok = "";
        	PacienteFlat pacFlat = new PacienteFlat(pac, ok);
         	pacsFlat.add(pacFlat);
        }
		
		return pacsFlat;

	}
	public List<PacienteFlat> findAllSqlInativo() {
		List<Paciente>pacs = repo.findAllSqlInativo();
		List<PacienteFlat>pacsFlat = new ArrayList<PacienteFlat>();
        for(Paciente pac: pacs) {
        	PacienteFlat pacFlat = new PacienteFlat(pac); 
        	pacsFlat.add(pacFlat);
        }
		
		
		
		
		return pacsFlat;
	}


	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Paciente buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("PacienteLog não encontrada", id)));
	}

	public Paciente from(Paciente atividadeNovo) {
		Paciente atividadeAtual = buscarOuFalhar(atividadeNovo.getId());
		BeanUtils.copyProperties(atividadeNovo, atividadeAtual, "id");
		atividadeAtual.setTenant(tenantUsuario.buscarOuFalhar());
		logPaciente(atividadeAtual, "alterar");
		return repo.save(atividadeAtual);
	}

	@Transactional
	public void status(Boolean obj, int id) {
		Paciente pac = buscarOuFalhar(id);
		pac.setStatus(obj);
		logPaciente(pac, "status");
	}

	public Page<PacienteFlat> mudarPacienteParaFlat(Page<Paciente> pacs) {
		List<PacienteFlat> cFlats = new ArrayList<PacienteFlat>();
		for (Paciente p : pacs.getContent()) {
			PacienteFlat cFlat = new PacienteFlat(p);
			cFlats.add(cFlat);

		}
		Page<PacienteFlat> page = new PageImpl<>(cFlats, pacs.getPageable(),
				pacs.getTotalElements());

		return page;
	}

}
