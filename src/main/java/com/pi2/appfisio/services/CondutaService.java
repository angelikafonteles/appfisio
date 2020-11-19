package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.repositories.CondutaRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class CondutaService {

	@Autowired
	private CondutaRepository repo;
	
	public Conduta findById(Integer id) {
		Optional<Conduta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Conduta.class.getName()));
	}
	
	public List<Conduta> findAll(){
		return repo.findAll();
	}
	
	public Conduta insert(Conduta obj){
        return repo.save(obj);
    }
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Conduta update(Integer id, Conduta obj) {
		Conduta entity = repo.getOne(id);
		updateData(entity, obj);
		return repo.save(entity);
	}
	
	private void updateData(Conduta entity, Conduta obj) {
		entity.setNome(obj.getNome());
	
	}
}
