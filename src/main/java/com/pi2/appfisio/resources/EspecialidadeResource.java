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

import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.services.EspecialidadeService;

@RestController
@RequestMapping(value = "especialidades")
public class EspecialidadeResource {
	
	@Autowired
	private EspecialidadeService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Especialidade obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Especialidade>> findAll(){
		List<Especialidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Especialidade> createNew(@RequestBody Especialidade obj) {
	    Especialidade savedObj = service.save(obj);
	    return ResponseEntity.ok(savedObj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
