package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.repositories.UsuarioRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario insert(Usuario obj){
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
	
	public Usuario update(Integer id, Usuario obj) {
		try{
			Usuario entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setDataNascimento(obj.getDataNascimento());
	
	}
		
}
