package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.dto.ConvenioDTO;
import com.ramazzotte.domain.dto.ConvenioNewDTO;
import com.ramazzotte.domain.dto.flat.ConvenioFlat;
import com.ramazzotte.domain.dto.flat.ExameConvenioItensFlat2;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.service.ConvenioService;
import com.ramazzotte.service.rels.RelAtendimento;


@RestController
@RequestMapping(value = "/convenios")
public class ConvenioResource {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ConvenioService service;
	@Autowired
	private RelAtendimento relservice;
	
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Convenio obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ConvenioFlat>> findAll() {
	//	List<ConvenioDTO> list = service.findAll();
		List<ConvenioFlat> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/exame/{idexame}", method = RequestMethod.GET)
	public ResponseEntity<List<ExameConvenioItensFlat2>> findAll(@PathVariable Integer idexame) {
	//	List<ConvenioDTO> list = service.findAll();
		List<ExameConvenioItensFlat2> list = service.findAllSQL(idexame);
		return ResponseEntity.ok().body(list);
	}
	

	@RequestMapping(value = "/conv/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<Convenio>> findAllConv(@PathVariable Integer id) {
	//	List<ConvenioDTO> list = service.findAll();
		List<Convenio> list = service.findAllConv(id);
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<ConvenioFlat>> findAllInativo() {
		List<ConvenioFlat> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/relatorios/convenio", method = RequestMethod.GET)
	@CheckSecurity.Atendimento.PodeConsultar
	public ResponseEntity<byte[]> convenio(
			@RequestParam Integer tenant) throws Exception {
		byte[] relatorio = relservice.convenio(tenant);		
				
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}


	@CheckSecurity.Convenio.PodeExcluir
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@CheckSecurity.Convenio.PodeCadastrar
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Convenio> insert(@Valid @RequestBody ConvenioNewDTO obj) {
		Convenio novoobj = modelMapper.map(obj, Convenio.class);
		Convenio objNovo = service.insert(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}

	@CheckSecurity.Convenio.PodeAtualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Convenio> update(@Valid @RequestBody ConvenioDTO obj, @PathVariable Integer id) {
		obj.setId(id);
		Convenio novoobj = new Convenio(obj);
		Convenio atividadeAtualizado = service.from(novoobj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);
		
	}
	@CheckSecurity.Convenio.PodeConsultar
	@RequestMapping(value = "/sql", method = RequestMethod.GET)
	public ResponseEntity<List<ConvenioFlat>> findAllSql() {
		List<ConvenioFlat> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	
	@CheckSecurity.Convenio.PodeAtualizar
	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		service.status(obj,id);
		

	}

}
