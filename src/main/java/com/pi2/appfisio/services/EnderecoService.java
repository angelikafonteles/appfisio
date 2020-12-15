package com.pi2.appfisio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Cidade;
import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.dto.EnderecoDTO;
import com.pi2.appfisio.repositories.EnderecoRepository;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;
	
	public Endereco findById(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Endereco update(Integer id, Endereco obj) {
		try{
			Endereco entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Endereco entity, Endereco obj) {
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setComplemento(obj.getComplemento());
		entity.setBairro(obj.getBairro());
		entity.setCep(obj.getCep());
		entity.setCidade(obj.getCidade());
	
	}
	
	public Endereco fromDTO(EnderecoDTO objDto) {
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		return new Endereco(objDto.getId(), objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cidade, null);
	}
}
