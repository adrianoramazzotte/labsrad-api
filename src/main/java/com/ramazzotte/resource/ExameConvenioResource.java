package com.ramazzotte.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.service.ExameService;


@RestController
@RequestMapping(value = "/exameconvenios")
public class ExameConvenioResource {
	
	@Autowired
	private ExameService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<ExameConvenio> obj = service.findAll();
		for(ExameConvenio ec:obj ) {
			System.out.println(ec.getId().getExame());
			System.out.println(ec.getId().getConvenio());					
				
		}
		return ResponseEntity.ok().body(obj);
	}
	

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Exame> insert(@RequestBody Exame obj) {
//		Exame objNovo = service.insert(obj);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
//				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
//		return ResponseEntity.created(uri).body(objNovo);
//		
//	}



}
