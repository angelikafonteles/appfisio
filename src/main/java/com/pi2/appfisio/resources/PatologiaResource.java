package com.pi2.appfisio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi2.appfisio.domain.Patologia;
import com.pi2.appfisio.dto.PatologiaDTO;
import com.pi2.appfisio.services.PatologiaService;

@RestController
@RequestMapping(value = "patologias")
public class PatologiaResource {
	
	@Autowired
	private PatologiaService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Patologia> findById(@PathVariable Integer id) {
		Patologia obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<PatologiaDTO>> findAll(){
		List<Patologia> list = service.findAll();
		List<PatologiaDTO> listDto = list.stream().map(obj -> new PatologiaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Patologia> insert(@Valid @RequestBody Patologia obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Patologia> update(@PathVariable Integer id, @Valid @RequestBody Patologia obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
