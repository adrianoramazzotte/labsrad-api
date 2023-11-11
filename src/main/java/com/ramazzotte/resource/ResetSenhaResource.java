package com.ramazzotte.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramazzotte.domain.dto.flat.EmailDTO;
import com.ramazzotte.service.ResetSenhaService;



@RestController
@RequestMapping(value = "/forgot")
public class ResetSenhaResource {
	@Autowired
	ResetSenhaService restsenhaService;

	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> resetSenha(@RequestBody String email){
		EmailDTO obj = new EmailDTO();
		obj.setEmail(email);
		restsenhaService.sendNewPassword(obj);		
		return ResponseEntity.ok().body(obj);
	}
	



}