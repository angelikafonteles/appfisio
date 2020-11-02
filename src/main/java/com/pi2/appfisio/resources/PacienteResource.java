package com.pi2.appfisio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi2.appfisio.domain.Paciente;
import com.pi2.appfisio.services.PacienteService;

@RestController
@RequestMapping(value = "pacientes")
public class PacienteResource {
	
	@Autowired
	private PacienteService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Paciente obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> findAll(){
		List<Paciente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Paciente> createNew(@RequestBody Paciente obj) {
	    Paciente savedObj = service.save(obj);
	    return ResponseEntity.ok(savedObj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Paciente paciente, @PathVariable Integer id){
		Paciente obj = service.fromPaciente(paciente);
		obj.setId(id);
		obj = service.updateById(obj);
		return ResponseEntity.noContent().build();
	}
}
