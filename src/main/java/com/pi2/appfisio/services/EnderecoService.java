package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.repositories.EnderecoRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	
	public Endereco findById(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public Endereco insert(Endereco obj){
        return repo.save(obj);
    }
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Endereco update(Integer id, Endereco obj) {
		Endereco entity = repo.getOne(id);
		updateData(entity, obj);
		return repo.save(entity);
	}
	
	private void updateData(Endereco entity, Endereco obj) {
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setComplemento(obj.getComplemento());
		entity.setBairro(obj.getBairro());
		entity.setCep(obj.getCep());
	
	}
		
}
