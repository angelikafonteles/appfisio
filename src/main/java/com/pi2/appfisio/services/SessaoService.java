package com.pi2.appfisio.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi2.appfisio.domain.Sessao;
import com.pi2.appfisio.repositories.CondutaRepository;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.repositories.SessaoRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository repo;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private PacienteRepository pacienteRepo;
	
	@Autowired
	private CondutaService condutaService;
	
	@Autowired
	private CondutaRepository condutaRepo;
	
	public Sessao findById(Integer id) {
		Optional<Sessao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Sessao> findAll(){
		return repo.findAll();
	}
	//erro
	@Transactional
	public Sessao insert(Sessao obj){
		obj.setData(new Date());
		obj.setObservacoes(obj.getObservacoes());
		obj = repo.save(obj);
		obj.setConduta(condutaService.findById(obj.getConduta().getId()));
		obj.setPaciente(pacienteService.findById(obj.getPaciente().getId()));
		pacienteRepo.save(obj.getPaciente());
		condutaRepo.save(obj.getConduta());
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
		entity.setData(obj.getData());
		entity.setObservacoes(obj.getObservacoes());
	
	}
	
	public Page<Sessao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
		
}
