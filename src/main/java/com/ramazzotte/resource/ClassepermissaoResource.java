package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.Classepermissao;
import com.ramazzotte.domain.dto.flat.ClassePermissaoFlat;
import com.ramazzotte.service.ClassepermissaoService;

import springfox.documentation.annotations.ApiIgnore;



@RestController
@RequestMapping(value = "/permissoes")
@ApiIgnore
public class ClassepermissaoResource {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ClassepermissaoService service;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findAll(@PathVariable Integer id) {
		List<ClassePermissaoFlat> list = service.findAllSQL(id);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<Classepermissao>> findAllInativo() {
		List<Classepermissao> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Classepermissao> insert(@Valid @RequestBody Classepermissao obj) {
		Classepermissao novoobj = modelMapper.map(obj, Classepermissao.class);
		Classepermissao objNovo = service.insert(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Classepermissao> update(@Valid @RequestBody Classepermissao obj, @PathVariable Integer id) {
		obj.setId(id);
		Classepermissao novoobj = modelMapper.map(obj, Classepermissao.class);
		Classepermissao atividadeAtualizado = service.from(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);
		
	}
	

	

}
