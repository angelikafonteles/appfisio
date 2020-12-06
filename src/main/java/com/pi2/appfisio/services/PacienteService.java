package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public Paciente findById(Integer id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}
	
	public List<Paciente> findAll(){
		return repo.findAll();
	}
	
	public Paciente insert(Paciente obj){
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
	
	public Paciente update(Integer id, Paciente obj) {
		try{
			Paciente entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Paciente entity, Paciente obj) {
		entity.setNome(obj.getNome());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setGenero(obj.getGenero());
		entity.setEmail(obj.getEmail());
		entity.setProfissao(obj.getProfissao());
		entity.setTelefones(obj.getTelefones());
	
	}
}
