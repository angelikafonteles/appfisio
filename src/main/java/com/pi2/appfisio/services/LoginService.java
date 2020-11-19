package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Login;
import com.pi2.appfisio.repositories.LoginRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repo;

	public Login findById(Integer id) {
		Optional<Login> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Login.class.getName()));
	}

	public Login findByLoginPass(String email, String senha) {
		Optional<Login> obj = repo.findByLoginPass(email, senha);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Email: " + email + " Senha:" + senha + ", Tipo: " + Login.class.getName()));
	}

	public Login insert(Login obj) {
		return repo.save(obj);
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

}
