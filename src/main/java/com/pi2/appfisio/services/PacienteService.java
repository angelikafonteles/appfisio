package com.pi2.appfisio.services;

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

import com.pi2.appfisio.domain.Cidade;
import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.domain.Estado;
import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.domain.enums.EstadoTipo;
import com.pi2.appfisio.domain.enums.Genero;
import com.pi2.appfisio.dto.PacienteDTO;
import com.pi2.appfisio.dto.PacienteNewDTO;
import com.pi2.appfisio.repositories.EnderecoRepository;
import com.pi2.appfisio.repositories.PacienteRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public Paciente findById(Integer id) {
		Optional<Paciente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));

	}
	
	public Paciente findByCpf(String cpf) {
		Optional<Paciente> obj = repo.findByCpf(cpf);
		return obj.orElseThrow(() -> new ResourceNotFoundException(cpf));

	}
	
	public Paciente findByName(String nome) {
		Optional<Paciente> obj = repo.findByNome(nome);
		return obj.orElseThrow(() -> new ResourceNotFoundException(nome));

	}
	
	public List<Paciente> findAll(){
		return repo.findAll();
	}
	
	@Transactional
	public Paciente insert(Paciente obj){
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.save(obj.getEndereco());
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
	
	public Paciente update(Integer id, Paciente obj) {
		try{
			Paciente entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Paciente entity, Paciente obj) {
		entity.setNome(obj.getNome());
		entity.setDataNascimento(obj.getDataNascimento());
		entity.setEmail(obj.getEmail());
		entity.setProfissao(obj.getProfissao());
		entity.setTelefones(obj.getTelefones());
	
	}
	
	public Paciente fromDTO(PacienteDTO objDto) {
		Paciente pac =  new Paciente(objDto.getId(), null, objDto.getNome(), objDto.getDataNascimento(), null, objDto.getEmail(), objDto.getProfissao(), new Usuario());
		pac.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			pac.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			pac.getTelefones().add(objDto.getTelefone3());
		}
		return pac;
	}
	
	public Paciente fromDTO(PacienteNewDTO objDto) {
		Usuario user =new Usuario(objDto.getUsuarioId(), null, null, null, null, null);
		Paciente pac = new Paciente(null, objDto.getCpf(), objDto.getNome(), objDto.getDataNascimento(), Genero.toEnum(objDto.getGenero()), objDto.getEmail(), objDto.getProfissao(), user);
		Estado estado = new Estado(null, EstadoTipo.toEnum(objDto.getEstadoId()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, estado);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cid, pac);
		pac.setEndereco(end);
		pac.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			pac.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			pac.getTelefones().add(objDto.getTelefone3());
		}
		return pac;
	}
	
	public Page<Paciente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
