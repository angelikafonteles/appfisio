package com.pi2.appfisio.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.dto.PacienteNewDTO;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.resources.exception.FieldMessage;

public class PacienteInsertValidator implements ConstraintValidator<PacienteInsert, PacienteNewDTO> {

	@Autowired
	private PacienteRepository repo;

	@Override
	public void initialize(PacienteInsert ann) {
	}

	@Override
	public boolean isValid(PacienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Paciente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
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

