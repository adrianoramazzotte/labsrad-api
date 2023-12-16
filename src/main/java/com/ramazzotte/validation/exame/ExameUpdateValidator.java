package com.ramazzotte.validation.exame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ramazzotte.domain.Exame;
import com.ramazzotte.domain.dto.flat.ExameFlatUpdate;
import com.ramazzotte.repository.ExameRepository;
import com.ramazzotte.resource.excepition.FieldMessage;
import com.ramazzotte.service.util.Tenantuser;





public class ExameUpdateValidator implements ConstraintValidator<ExameUpdate, ExameFlatUpdate> {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private Tenantuser tenantUsuario;
	@Autowired
	private ExameRepository repo;

	@Override
	public void initialize(ExameUpdate ann) {
	}

	@Override
	public boolean isValid(ExameFlatUpdate objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Map<String,String>map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));	
		
		Exame aux = repo.findByDescricaoAndTenant(objDto.getDescricao(),tenantUsuario.buscarOuFalhar() );

		if(aux !=null && !aux.getId().equals(uriId)) {
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
