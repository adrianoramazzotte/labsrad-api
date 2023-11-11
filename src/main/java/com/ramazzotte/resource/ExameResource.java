package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.dto.flat.ExameFlatInsert;
import com.ramazzotte.domain.dto.flat.ExameFlatUpdate;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.service.ExameService;


@RestController
@RequestMapping(value = "/exames")
public class ExameResource {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ExameService service;

	@CheckSecurity.Exame.PodeConsultar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		ExameFlatInsert obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Exame.PodeConsultar
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<ExameFlatInsert> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Exame.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<ExameFlatInsert>> findAllInativo() {
		List<ExameFlatInsert> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Exame.PodeExcluir
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@CheckSecurity.Exame.PodeCadastrar
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Exame> insert(@Valid @RequestBody ExameFlatInsert obj) {
		ExameFlatInsert novoobj = modelMapper.map(obj, ExameFlatInsert.class);
		Exame objNovo = service.insert(novoobj,obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}

	@CheckSecurity.Exame.PodeAtualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@Valid @RequestBody ExameFlatUpdate obj, @PathVariable Integer id) {
		obj.setId(id);
		service.from(obj);
		List<ExameFlatInsert> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
		
	}
	
	
	@CheckSecurity.Exame.PodeAtualizar
	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		service.status(obj,id);
		

	}

}