package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.domain.Usuario;
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
	
	public Paciente save(Paciente obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
	
		repo.deleteById(id);
	}
	
	public Paciente updateById(Paciente obj) {
		Paciente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Paciente newObj, Paciente obj) {
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setGenero(obj.getGenero());
		newObj.setEmail(obj.getEmail());
		newObj.setProfissao(obj.getProfissao());
		newObj.setTelefones(obj.getTelefones());
	
	}
	
	public Paciente fromPaciente(Paciente obj) {
		return new Paciente(obj.getId(), obj.getNome(), obj.getCpf(), obj.getDataNascimento(), obj.getGenero(), obj.getEmail(),
				obj.getProfissao(), new Usuario());
	}
		
}
