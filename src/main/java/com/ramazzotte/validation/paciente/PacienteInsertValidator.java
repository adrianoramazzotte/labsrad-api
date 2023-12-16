package com.ramazzotte.validation.paciente;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.dto.PacienteNewDTO;
import com.ramazzotte.repository.PacienteRepository;
import com.ramazzotte.resource.excepition.FieldMessage;



public class PacienteInsertValidator implements ConstraintValidator<PacienteInsert, PacienteNewDTO> {
	@Autowired
	private PacienteRepository repo;
	@Override
	public void initialize(PacienteInsert ann) {	}
	@Override
	public boolean isValid(PacienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Paciente aux1 = repo.findByCpf(objDto.getCpf());
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
