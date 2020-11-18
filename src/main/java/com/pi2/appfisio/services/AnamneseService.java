package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Anamnese;
import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.repositories.AnamneseRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class AnamneseService {

	@Autowired
	private AnamneseRepository repo;
	
	public Anamnese findById(Integer id) {
		Optional<Anamnese> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Anamnese.class.getName()));
	}
	
	public List<Anamnese> findAll(){
		return repo.findAll();
	}
	
	public Anamnese insert(Anamnese obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public Anamnese updateById(Anamnese obj) {
		Anamnese newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Anamnese newObj, Anamnese obj) {
		newObj.setDataDaFicha(obj.getDataDaFicha());
		newObj.setPressaoArterial(obj.getPressaoArterial());
		newObj.setFrequenciaCardiaca(obj.getFrequenciaCardiaca());
		newObj.setHistoricoFamiliar(obj.getHistoricoFamiliar());
		newObj.setHistoriaPatologiaPregressa(obj.getHistoriaPatologiaPregressa());
		newObj.setHistoricoDoencaAtual(obj.getHistoricoDoencaAtual());
		newObj.setQueixaPrincipal(obj.getQueixaPrincipal());
		newObj.setObservacoes(obj.getObservacoes());
	
	}
	
	public Anamnese fromAnamnese(Anamnese obj) {
		return new Anamnese(obj.getId(), obj.getDataDaFicha(), obj.getPressaoArterial(), obj.getFrequenciaCardiaca(), obj.getHistoricoFamiliar(),
				obj.getHistoriaPatologiaPregressa(), obj.getHistoricoDoencaAtual(), obj.getQueixaPrincipal(), obj.getObservacoes(),
				new Paciente(), new Patologia());
	}
		
}
