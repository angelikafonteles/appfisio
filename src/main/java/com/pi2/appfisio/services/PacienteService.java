package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	public Paciente findById(Integer id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
	
	public List<Paciente> findAll(){
		return repo.findAll();
	}
	
	public Paciente insert(Paciente obj){
        return repo.save(obj);
    }
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Paciente update(Integer id, Paciente obj) {
		Paciente entity = repo.getOne(id);
		updateData(entity, obj);
		return repo.save(entity);
	}
	
	private void updateData(Paciente entity, Paciente obj) {
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setGenero(obj.getGenero());
		entity.setEmail(obj.getEmail());
		entity.setProfissao(obj.getProfissao());
		entity.setTelefones(obj.getTelefones());
	
	}
}
