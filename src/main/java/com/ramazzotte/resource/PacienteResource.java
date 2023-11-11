package com.ramazzotte.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.dto.PacienteDTO;
import com.ramazzotte.domain.dto.PacienteNewDTO;
import com.ramazzotte.domain.dto.flat.PacienteFlat;
import com.ramazzotte.repository.PacienteRepository;
import com.ramazzotte.repository.filter.PacienteFilter;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.service.PacienteService;
import com.ramazzotte.service.util.UtilGeral;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PacienteService service;
	@Autowired
	private PacienteRepository pacienteRepo;

	@CheckSecurity.Paciente.PodeConsultar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Paciente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Paciente.PodeConsultar
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PacienteFlat>> findAll() {
		List<PacienteFlat> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	
	
	@RequestMapping(value = "/filtro", method = RequestMethod.GET)
	public Page<PacienteFlat> findAllPag(PacienteFilter pacienteFilter, Pageable pageable) {

		Page<Paciente> pacs = pacienteRepo.filtrar(pacienteFilter, pageable);
		Page<PacienteFlat> pacsflat = service.mudarPacienteParaFlat(pacs);
		return pacsflat;

	}
	
	
	
	@CheckSecurity.Paciente.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<PacienteFlat>> findAllInativo() {
		List<PacienteFlat> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Paciente.PodeConsultar
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Paciente>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Paciente> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Paciente.PodeExcluir
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@CheckSecurity.Paciente.PodeCadastrar
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Paciente> insert(@Valid @RequestBody PacienteNewDTO obj) {
		Paciente novoobj = modelMapper.map(obj, Paciente.class);
		UtilGeral ug = new UtilGeral();
		Date datanascimento = ug.localDataParaData(obj.getDatanasc());
		novoobj.setDatanasc(datanascimento);
	    Paciente objNovo = service.insert(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}

	@CheckSecurity.Paciente.PodeAtualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Paciente> update(@Valid @RequestBody PacienteDTO obj, @PathVariable Integer id) {
		obj.setId(id);
		Paciente novoobj = modelMapper.map(obj, Paciente.class);
		UtilGeral ug = new UtilGeral();
		Date datanascimento = ug.localDataParaData(obj.getDatanasc());
		novoobj.setDatanasc(datanascimento);
		Paciente atividadeAtualizado = service.from(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);		
	}
//	@CheckSecurity.Paciente.PodeConsultar
//	@RequestMapping(value = "/sql", method = RequestMethod.GET)
//	public ResponseEntity<List<Paciente>> findAllSql() {
//		List<Paciente> list = service.findAllSQL();
//		return ResponseEntity.ok().body(list);
//	}	
	
	@CheckSecurity.Paciente.PodeAlterarStatus
	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		service.status(obj,id);
		

	}

}
