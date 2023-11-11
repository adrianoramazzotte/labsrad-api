package com.ramazzotte.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramazzotte.domain.ExameConvenio;
import com.ramazzotte.domain.dto.flat.ExameConvenioItensFlat;
import com.ramazzotte.domain.dto.flat.ExameFlatInsert;
import com.ramazzotte.repository.ExameConvenioRepository;
import com.ramazzotte.security.resource.CheckSecurity;
import com.ramazzotte.service.ExameService;


@RestController
@RequestMapping(value = "/examesconv")
public class ExameConvenioResource {

	@Autowired
	private ExameConvenioRepository repoExaConv;
	@Autowired
	private ExameService exameservice;
	@CheckSecurity.Exame.PodeConsultar
	@RequestMapping(value = "/{idExame}/{idConvenio}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer idExame, @PathVariable Integer idConvenio ) {
		ExameConvenio obj = repoExaConv.buscarExameConvenio(idExame, idConvenio );
		ExameConvenioItensFlat obj1 = new ExameConvenioItensFlat(obj);
		return ResponseEntity.ok().body(obj1);
	}
	@CheckSecurity.Exame.PodeConsultar
	@RequestMapping(value = "/{idConvenio}", method = RequestMethod.GET)
	public ResponseEntity<List<ExameFlatInsert>> find(@PathVariable int idConvenio ) {
		List<ExameFlatInsert> obj = exameservice.findPorConvenio(idConvenio);
		return ResponseEntity.ok().body(obj);
	}







}
