package com.ramazzotte.validation.convenio;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.dto.ConvenioNewDTO;
import com.ramazzotte.repository.ConvenioRepository;
import com.ramazzotte.resource.excepition.FieldMessage;



public class ConvenioInsertValidator implements ConstraintValidator<ConvenioInsert, ConvenioNewDTO> {
	@Autowired
	private ConvenioRepository repo;
	@Override
	public void initialize(ConvenioInsert ann) {	}
	@Override
	public boolean isValid(ConvenioNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Convenio aux1 = repo.findByDescricao(objDto.getDescricao());
		if(aux1 !=null) {
			list.add(new FieldMessage("descricao"," Descrição já existente"));
		}	

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
