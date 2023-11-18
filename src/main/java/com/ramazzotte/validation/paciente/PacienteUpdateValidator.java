package com.ramazzotte.validation.paciente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ramazzotte.domain.Paciente;
import com.ramazzotte.domain.dto.PacienteDTO;
import com.ramazzotte.repository.PacienteRepository;
import com.ramazzotte.resource.excepition.FieldMessage;
import com.ramazzotte.resource.util.BR;
import com.ramazzotte.service.util.Tenantuser;
import com.ramazzotte.service.util.UtilGeral;





public class PacienteUpdateValidator implements ConstraintValidator<PacienteUpdate, PacienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PacienteRepository repo;
	@Autowired
	private Tenantuser tenantUsuario;
	@Override
	public void initialize(PacienteUpdate ann) {
	}

	@Override
	public boolean isValid(PacienteDTO objDto, ConstraintValidatorContext context) {
		UtilGeral ug = new UtilGeral();
		Date datanascimento = ug.localDataParaData(objDto.getDatanasc());
		List<FieldMessage> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Map<String,String>map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));	
		
		Paciente aux = repo.findByNomeAndDatanascAndTenant(objDto.getNome(), datanascimento,tenantUsuario.buscarOuFalhar() );
		if(aux !=null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("Nome"," Nome com data nascimento já existente"));
		}
		
		Paciente aux1 = repo.findByCpfAndTenant(objDto.getCpf(),tenantUsuario.buscarOuFalhar() );
		if(aux1 !=null && !aux1.getId().equals(uriId)) {
			list.add(new FieldMessage("cpf"," Cpf já existente"));
		}
		
		if(!BR.isValidCPF(objDto.getCpf())) {
			list.add(new FieldMessage("cpf","CPF inválido  "));
			
		}
			

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
