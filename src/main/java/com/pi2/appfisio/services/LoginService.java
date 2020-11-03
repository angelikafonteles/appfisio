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
	
	public Login save(Login obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
		
		repo.deleteById(id);
	}
		
}
