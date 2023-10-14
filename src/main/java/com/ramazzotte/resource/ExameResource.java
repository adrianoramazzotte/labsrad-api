package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.service.ExameService;


@RestController
@RequestMapping(value = "/exames")
public class ExameResource {
	@Autowired
	private ExameService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Exame obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Exame> list = service.findAllSQL();
//		for(Exame e:list) {
//			e.setExamesConvenio(null);
//		}
		return ResponseEntity.ok().body(list);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Exame> insert( @RequestBody Exame obj) {
		Exame objNovo = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Exame obj, @PathVariable Integer id) {
		obj.setId(id);
		Exame exame = service.from(obj);
		return ResponseEntity.ok().body(exame);
		
	}
//	
//	
//
//	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
//		service.status(obj,id);
//		
//
//	}

}