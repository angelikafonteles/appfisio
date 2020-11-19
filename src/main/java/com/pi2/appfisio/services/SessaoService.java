package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Sessao;
import com.pi2.appfisio.repositories.SessaoRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository repo;
	
	public Sessao findById(Integer id) {
		Optional<Sessao> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Sessao.class.getName()));
	}
	
	public List<Sessao> findAll(){
		return repo.findAll();
	}
	
	public Sessao insert(Sessao obj){
        return repo.save(obj);
    }
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Sessao update(Integer id, Sessao obj) {
		Sessao entity = repo.getOne(id);
		updateData(entity, obj);
		return repo.save(entity);
	}
	
	private void updateData(Sessao entity, Sessao obj) {
		entity.setData(obj.getData());
		entity.setObservacoes(obj.getObservacoes());
	
	}
		
}
