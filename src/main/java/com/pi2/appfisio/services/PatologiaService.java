package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.repositories.PatologiaRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class PatologiaService {

	@Autowired
	private PatologiaRepository repo;
	
	public Patologia findById(Integer id) {
		Optional<Patologia> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}
	
	public List<Patologia> findAll(){
		return repo.findAll();
	}
	
	public Patologia insert(Patologia obj){
        return repo.save(obj);
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
	
	public Patologia update(Integer id, Patologia obj) {
		try{
			Patologia entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Patologia entity, Patologia obj) {
		entity.setNome(obj.getNome());
	
	}
		
}
