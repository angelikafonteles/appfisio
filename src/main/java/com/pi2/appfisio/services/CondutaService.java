package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.domain.Sessao;
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
	
	public Conduta save(Conduta obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
	
		repo.deleteById(id);
	}
	
	public Conduta updateById(Conduta obj) {
		Conduta newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Conduta newObj, Conduta obj) {
		newObj.setNome(obj.getNome());
		newObj.setSessao(obj.getSessao());
	
	}
	
	public Conduta fromConduta(Conduta obj) {
		return new Conduta(obj.getId(), obj.getNome(), new Sessao(), new Patologia());
	}
		
}
