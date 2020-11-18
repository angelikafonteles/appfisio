package com.pi2.appfisio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi2.appfisio.domain.Especialidade;
import com.pi2.appfisio.services.EspecialidadeService;

@RestController
@RequestMapping(value = "especialidades")
public class EspecialidadeResource {
	
	@Autowired
	private EspecialidadeService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Especialidade> findById(@PathVariable Integer id) {
		Especialidade obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Especialidade>> findAll(){
		List<Especialidade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Especialidade> insert(@RequestBody Especialidade obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
