package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Anamnese;
import com.pi2.appfisio.repositories.AnamneseRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository repo;
	
	public Anamnese findById(Integer id) {
		Optional<Anamnese> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Anamnese> findAll(){
		return repo.findAll();
	}
	
	public Anamnese insert(Anamnese obj){
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
	
	public Anamnese update(Integer id, Anamnese obj) {
		try{
			Anamnese entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Anamnese entity, Anamnese obj) {
		entity.setDataDaFicha(obj.getDataDaFicha());
		entity.setPressaoArterial(obj.getPressaoArterial());
		entity.setFrequenciaCardiaca(obj.getFrequenciaCardiaca());
		entity.setHistoricoFamiliar(obj.getHistoricoFamiliar());
		entity.setHistoriaPatologiaPregressa(obj.getHistoriaPatologiaPregressa());
		entity.setHistoricoDoencaAtual(obj.getHistoricoDoencaAtual());
		entity.setQueixaPrincipal(obj.getQueixaPrincipal());
		entity.setObservacoes(obj.getObservacoes());
	
	}
}
