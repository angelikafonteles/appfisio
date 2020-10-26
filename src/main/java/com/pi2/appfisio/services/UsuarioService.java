package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.repositories.UsuarioRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario buscar(Integer id) {
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
		
}
