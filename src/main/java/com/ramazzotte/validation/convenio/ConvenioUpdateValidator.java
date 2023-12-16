package com.ramazzotte.validation.convenio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ramazzotte.domain.Convenio;
import com.ramazzotte.domain.dto.ConvenioDTO;
import com.ramazzotte.repository.ConvenioRepository;
import com.ramazzotte.resource.excepition.FieldMessage;





public class ConvenioUpdateValidator implements ConstraintValidator<ConvenioUpdate, ConvenioDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ConvenioRepository repo;

	@Override
	public void initialize(ConvenioUpdate ann) {
	}

	@Override
	public boolean isValid(ConvenioDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Map<String,String>map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));	
		
		Convenio aux = repo.findByDescricao(objDto.getDescricao());
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
