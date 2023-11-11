package com.ramazzotte.validation.exame;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.dto.flat.ExameFlatInsert;
import com.ramazzotte.repository.ExameRepository;
import com.ramazzotte.resource.excepition.FieldMessage;
import com.ramazzotte.service.util.Tenantuser;



public class ExameInsertValidator implements ConstraintValidator<ExameInsert, ExameFlatInsert> {
	@Autowired
	private ExameRepository repo;
	@Autowired
	private Tenantuser tenantUsuario;
	@Override
	public void initialize(ExameInsert ann) {
	}

	@Override
	public boolean isValid(ExameFlatInsert objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
		
        Exame aux1 = repo.findByDescricaoAndTenant(objDto.getDescricao(),tenantUsuario.buscarOuFalhar() );
		if(aux1 !=null) {
			list.add(new FieldMessage("descricao"," Descrição já existente"));
		}	

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
