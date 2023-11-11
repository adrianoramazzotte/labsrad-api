package com.ramazzotte.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.fasterxml.jackson.annotation.JsonView;
import com.ramazzotte.domain.Usuario;
import com.ramazzotte.domain.dto.UsuarioDTO;
import com.ramazzotte.domain.dto.flat.UsuarioFlat;
import com.ramazzotte.domain.dto.viewretorno.UsuarioView;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@CheckSecurity.Usuario.PodeConsultar
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		UsuarioDTO obj = service.findF(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Usuario.PodeConsultar
	@JsonView(UsuarioView.Resumo1.class) 
	@RequestMapping(value = "/{id}/senha", method = RequestMethod.GET)
	public ResponseEntity<?> findSenha(@PathVariable Integer id) {
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@CheckSecurity.Usuario.PodeConsultar
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioFlat>> findAll() {
		List<UsuarioFlat> list = service.findAllSQL();
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Usuario.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioFlat>> findAllInativo() {
	//	List<UsuarioDTO> list = service.findAll();
		List<UsuarioFlat> list = service.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Usuario.PodeConsultar
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Usuario> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	@CheckSecurity.Usuario.PodeExcluir
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@CheckSecurity.Usuario.PodeCadastrar
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody UsuarioFlat obj) {		
		UsuarioFlat objNovo = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(objNovo);
		
	}
	


	@CheckSecurity.Usuario.PodeAtualizar
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody UsuarioFlat obj, @PathVariable Integer id) {
		obj.setId(id);
		UsuarioFlat atividadeAtualizado = service.from(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);
		
	}
	@CheckSecurity.Usuario.PodeAtualizar
	@RequestMapping(value = "/{id}/senha", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSenha(@RequestBody UsuarioFlat obj, @PathVariable Integer id) {
		obj.setId(id);
		UsuarioFlat atividadeAtualizado = service.fromSenha(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
		return ResponseEntity.created(uri).body(atividadeAtualizado);
		
	}
	

	@RequestMapping(value = "/alterarsenha", method = RequestMethod.PUT)
	public ResponseEntity<?> alterarsenha(@RequestBody String obj) {
		Usuario usu = service.fromSenha(obj);
		usu.setPermissoes(null);
		return ResponseEntity.ok().body(usu);

		
	}
//	@CheckSecurity.Usuario.PodeConsultar
//	@RequestMapping(value = "/sql", method = RequestMethod.GET)
//	public ResponseEntity<List<Usuario>> findAllSql() {
//		List<Usuario> list = service.findAllSQL();
//		return ResponseEntity.ok().body(list);
//	}
	
	
	@CheckSecurity.Usuario.PodeAlterarStatus
	@RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		service.status(obj,id);
		

	}
	
	@RequestMapping(value="/tenant/{idempresa}",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void setTenantAtivo(@PathVariable int idempresa)	{
		service.tenantAtivo(idempresa);
		

	}

}
