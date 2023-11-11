package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.Empresa;
import com.ramazzotte.domain.EmpresaNew;
import com.ramazzotte.domain.dto.flat.EmpresaFlat;
import com.ramazzotte.service.EmpresaService;


@RestController	
@RequestMapping(value="/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService service;



	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Empresa obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EmpresaFlat>> findAll() {
		List<EmpresaFlat> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ResponseEntity<List<EmpresaFlat>> findAllUsuario() {
		
		List<EmpresaFlat> list = service.findAllUsuario();
		return ResponseEntity.ok().body(list);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/inativos",method = RequestMethod.GET)
	public ResponseEntity<List<EmpresaFlat>> findAllInativos() {
		List<EmpresaFlat> list = service.findAllInativos();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Empresa> insert(@Valid @RequestBody EmpresaNew obj) {
		Empresa obj1 = new Empresa(obj);
		Empresa objNovo = service.insert(obj1);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objNovo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(objNovo);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Empresa> update(@Valid @RequestBody Empresa obj, @PathVariable Integer id) {
		obj.setId(id);
		Empresa empresaAtualizado = service.from(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(empresaAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(empresaAtualizado);
	}
	
}



