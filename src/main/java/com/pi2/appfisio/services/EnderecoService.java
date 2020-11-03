package com.pi2.appfisio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Cidade;
import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.domain.Paciente;
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
	
	public Endereco save(Endereco obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
	
		repo.deleteById(id);
	}
	
	public Endereco updateById(Endereco obj) {
		Endereco newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());
		newObj.setComplemento(obj.getComplemento());
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
	
	}
	
	public Endereco fromEndereco(Endereco obj) {
		return new Endereco(obj.getId(), obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(),
				new Cidade(), new Paciente());
	}
		
}
