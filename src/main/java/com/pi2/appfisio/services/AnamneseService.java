package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Anamnese;
import com.pi2.appfisio.repositories.AnamneseRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository repo;
	
	public Anamnese buscar(Integer id) {
		
		Optional<Anamnese> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Anamnese.class.getName()));
	}
		
}
