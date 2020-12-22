package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.domain.CondutaTecnica;
import com.pi2.appfisio.repositories.CondutaRepository;
import com.pi2.appfisio.repositories.CondutaTecnicaRepository;
import com.pi2.appfisio.repositories.PatologiaRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class CondutaService {

	@Autowired
	private CondutaRepository repo;
	
	@Autowired
	private CondutaTecnicaRepository condutaTecnicaRepo;
	
	@Autowired
	private PatologiaRepository patologiaRepo;
	
	@Autowired
	private TecnicaService tecnicaService;
	
	@Autowired
	private PatologiaService patologiaService;
	
	public Conduta findById(Integer id) {
		Optional<Conduta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Conduta> findAll(){
		return repo.findAll();
	}
	
	@Transactional
	public Conduta insert(Conduta obj){
		obj.setNome(obj.getNome());
		obj.setPatologia(patologiaService.findById(obj.getPatologia().getId()));
		obj.getPatologia().getCondutas().add(obj);
		obj = repo.save(obj);
		patologiaRepo.save(obj.getPatologia());
		for(CondutaTecnica x : obj.getTecnicas()) {
			x.setDescricao(x.getDescricao());
			x.setTecnica(tecnicaService.findById(x.getTecnica().getId()));
			x.setConduta(obj);
		}
       condutaTecnicaRepo.saveAll(obj.getTecnicas());
       return obj;
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
