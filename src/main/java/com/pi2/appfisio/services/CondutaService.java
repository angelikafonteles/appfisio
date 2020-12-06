package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.repositories.CondutaRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class CondutaService {

	@Autowired
	private CondutaRepository repo;
	
	public Conduta findById(Integer id) {
		Optional<Conduta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Conduta> findAll(){
		return repo.findAll();
	}
	
	public Conduta insert(Conduta obj){
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
	
	public Conduta update(Integer id, Conduta obj) {
		try{
			Conduta entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Conduta entity, Conduta obj) {
		entity.setNome(obj.getNome());
	
	}
}
