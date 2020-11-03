package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.repositories.PatologiaRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class PatologiaService {

	@Autowired
	private PatologiaRepository repo;
	
	public Patologia findById(Integer id) {
		
		Optional<Patologia> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Patologia.class.getName()));
	}
	
	public List<Patologia> findAll(){
		return repo.findAll();
	}
	
	public Patologia save(Patologia obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
	
		repo.deleteById(id);
	}
		
}
