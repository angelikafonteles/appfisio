package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Sessao;
import com.pi2.appfisio.repositories.SessaoRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository repo;
	
	public Sessao findById(Integer id) {
		Optional<Sessao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Sessao> findAll(){
		return repo.findAll();
	}
	
	public Sessao insert(Sessao obj){
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
	
	public Sessao update(Integer id, Sessao obj) {
		try{
			Sessao entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Sessao entity, Sessao obj) {
		entity.setInstante(obj.getInstante());
		entity.setObservacoes(obj.getObservacoes());
	
	}
		
}
