package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Tecnica;
import com.pi2.appfisio.repositories.TecnicaRepository;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class TecnicaService {

	@Autowired
	private TecnicaRepository repo;
	
	public Tecnica findById(Integer id) {
		Optional<Tecnica> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Tecnica> findAll(){
		return repo.findAll();
	}
		
}
