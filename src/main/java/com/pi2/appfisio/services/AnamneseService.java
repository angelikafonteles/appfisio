package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi2.appfisio.domain.Anamnese;
import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.dto.AnamneseNewDTO;
import com.pi2.appfisio.repositories.AnamneseRepository;
import com.pi2.appfisio.repositories.EspecialidadeRepository;
import com.pi2.appfisio.repositories.PatologiaRepository;
import com.pi2.appfisio.services.exceptios.DatabaseException;
import com.pi2.appfisio.services.exceptios.ResourceNotFoundException;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository repo;
	@Autowired
	private PatologiaRepository patologiaRepo;
	@Autowired
	private EspecialidadeRepository especialidadeRepo;
	
	public Anamnese findById(Integer id) {
		Optional<Anamnese> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<Anamnese> findAll(){
		return repo.findAll();
	}
	
	@Transactional
	public Anamnese insert(Anamnese obj){
        obj = repo.save(obj);
        patologiaRepo.save(obj.getPatologia());
        Patologia p = obj.getPatologia();
        especialidadeRepo.save(p.getEspecialidade());
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
	
	public Anamnese update(Integer id, Anamnese obj) {
		try{
			Anamnese entity = repo.getOne(id);
			updateData(entity, obj);
			return repo.save(entity);
			}catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException(id);
			}
	}
	
	private void updateData(Anamnese entity, Anamnese obj) {
		entity.setDataDaFicha(obj.getDataDaFicha());
		entity.setPressaoArterial(obj.getPressaoArterial());
		entity.setFrequenciaCardiaca(obj.getFrequenciaCardiaca());
		entity.setHistoricoFamiliar(obj.getHistoricoFamiliar());
		entity.setHistoriaPatologiaPregressa(obj.getHistoriaPatologiaPregressa());
		entity.setHistoricoDoencaAtual(obj.getHistoricoDoencaAtual());
		entity.setQueixaPrincipal(obj.getQueixaPrincipal());
		entity.setObservacoes(obj.getObservacoes());
	
	}
	
	public Anamnese fromDTO(AnamneseNewDTO objDto) {
		Paciente paciente = new Paciente(objDto.getPacienteId(), null, null, null, null, null, null, null);
		Especialidade especialidade = new Especialidade(null, objDto.getEspecialidade());
		Patologia patologia = new Patologia(null, objDto.getPatologia(), especialidade);
		Anamnese anamnese = new Anamnese(null, objDto.getDataDaFicha(), objDto.getPressaoArterial(), objDto.getFrequenciaCardiaca(), objDto.getHistoricoFamiliar(), objDto.getHistoriaPatologiaPregressa(), objDto.getHistoricoDoencaAtual(), objDto.getQueixaPrincipal(), objDto.getObservacoes(), paciente, patologia);
		paciente.getAnamneses().add(anamnese);
		patologia.getAnamneses().add(anamnese);
		return anamnese;
	}
}
