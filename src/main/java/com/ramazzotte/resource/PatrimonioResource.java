package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.Patrimonio;
import com.ramazzotte.domain.dto.flat.PatrimonioFlat;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.service.PatrimonioService;


@RestController
@RequestMapping(value = "/patrimonio")
public class PatrimonioResource {
	@Autowired
	private PatrimonioService service;

	
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Patrimonio obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PatrimonioFlat>> findAll() {
	//	List<ConvenioDTO> list = service.findAll();
		List<PatrimonioFlat> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	

	@RequestMapping(value = "/conv/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<Patrimonio>> findAllConv(@PathVariable Integer id) {
	//	List<ConvenioDTO> list = service.findAll();
		List<Patrimonio> list = service.findAllConv(id);
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<PatrimonioFlat>> findAllInativo() {
	//	List<ConvenioDTO> list = service.findAll();
		List<PatrimonioFlat> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
	
	


	@CheckSecurity.Convenio.PodeExcluir
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@CheckSecurity.Convenio.PodeCadastrar
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Patrimonio> insert(@Valid @RequestBody Patrimonio obj) {
		Patrimonio novoobj = new Patrimonio(obj);
		Patrimonio objNovo = service.insert(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}

	@CheckSecurity.Convenio.PodeAtualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Patrimonio> update(@Valid @RequestBody Patrimonio obj, @PathVariable Integer id) {
		obj.setId(id);
		Patrimonio novoobj = new Patrimonio(obj);
		Patrimonio atividadeAtualizado = service.from(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);
		
	}
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/sql", method = RequestMethod.GET)
	public ResponseEntity<List<PatrimonioFlat>> findAllSql() {
		List<PatrimonioFlat> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	
	@CheckSecurity.Convenio.PodeAtualizar
	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		service.status(obj,id);
		

	}

}
