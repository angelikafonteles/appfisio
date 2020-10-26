package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public Paciente buscar(Integer id) {
		
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
		
}
