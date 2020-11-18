package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.domain.Paciente;
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
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public Sessao updateById(Sessao obj) {
		Sessao newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Sessao newObj, Sessao obj) {
		newObj.setData(obj.getData());
		newObj.setObservacoes(obj.getObservacoes());
	
	}
	
	public Sessao fromSessao(Sessao obj) {
		return new Sessao(obj.getId(), obj.getData(), obj.getObservacoes(), new Paciente(), new Conduta());
	}
		
}
