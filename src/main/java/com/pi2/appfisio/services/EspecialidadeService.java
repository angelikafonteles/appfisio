package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.repositories.EspecialidadeRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository repo;
	
	public Especialidade findById(Integer id) {
		
		Optional<Especialidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Especialidade.class.getName()));
	}
	
	public List<Especialidade> findAll(){
		return repo.findAll();
	}
	
	public Especialidade save(Especialidade obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
	
		repo.deleteById(id);
	}
}
