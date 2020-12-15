package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Login;
import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.dto.LoginNewDTO;
import com.pi2.appfisio.dto.PerfilDTO;
import com.pi2.appfisio.repositories.LoginRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repo;

	public Login findById(Integer id) {
		Optional<Login> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Login findByLoginPass(String email, String senha) {
		Optional<Login> obj = repo.findByLoginPass(email, senha);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Email: " + email + " Senha:" + senha + ", Tipo: " + Login.class.getName()));
	}

	public Login insert(Login obj) {
		obj = repo.save(obj);
		return obj;
	}
	
	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Login fromDTO(LoginNewDTO objDto) {
		return new Login(null, objDto.getEmail(), objDto.getSenha());
	}
	
	public Login perfilDTO(PerfilDTO objDto) {
		Login login = findById(objDto.getId());
		Usuario user = new Usuario(null, objDto.getNome(), objDto.getCpf(), objDto.getDataNascimento(), objDto.getOrgaoDeClasse(), login);
		login.setUsuario(user);
		return login;
	}

}
