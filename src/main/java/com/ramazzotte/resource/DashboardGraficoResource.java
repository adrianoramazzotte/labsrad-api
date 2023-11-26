package com.ramazzotte.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramazzotte.domain.dto.rel.AtendimentoDashboarGrafico;
import com.ramazzotte.service.DashService;

import springfox.documentation.annotations.ApiIgnore;
@RestController
@RequestMapping(value = "/graficos")
@ApiIgnore
public class DashboardGraficoResource {
	
	@Autowired
	private DashService ds;
	
	@RequestMapping( method = RequestMethod.GET)	
	public ResponseEntity<?> find() {
		AtendimentoDashboarGrafico obj = ds.buscar();
		return ResponseEntity.ok().body(obj);
	}

}
