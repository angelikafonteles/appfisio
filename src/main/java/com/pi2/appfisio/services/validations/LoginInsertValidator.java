package com.pi2.appfisio.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pi2.appfisio.domain.Login;
import com.pi2.appfisio.dto.LoginNewDTO;
import com.pi2.appfisio.repositories.LoginRepository;
import com.pi2.appfisio.resources.exception.FieldMessage;

public class LoginInsertValidator implements ConstraintValidator<LoginInsert, LoginNewDTO> {

	@Autowired
	private LoginRepository repo;

	@Override
	public void initialize(LoginInsert ann) {
	}

	@Override
	public boolean isValid(LoginNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Login aux = repo.findByEmail(objDto.getEmail());
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

