package com.pi2.appfisio.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.dto.PacienteDTO;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.resources.exception.FieldMessage;

public class PacienteUpdateValidator implements ConstraintValidator<PacienteUpdate, PacienteDTO> {

	@Autowired
	private PacienteRepository repo;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(PacienteUpdate ann) {
	}

	@Override
	public boolean isValid(PacienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Paciente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

