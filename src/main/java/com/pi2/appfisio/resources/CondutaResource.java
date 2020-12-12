package com.pi2.appfisio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.pi2.appfisio.domain.Conduta;
import com.pi2.appfisio.dto.CondutaDTO;
import com.pi2.appfisio.services.CondutaService;

@RestController
@RequestMapping(value = "condutas")
public class CondutaResource {
	
	@Autowired
	private CondutaService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Conduta> findById(@PathVariable Integer id) {
		Conduta obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<CondutaDTO>> findAll(){
		List<Conduta> list = service.findAll();
		List<CondutaDTO> listDto = list.stream().map(obj -> new CondutaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Conduta> insert(@RequestBody Conduta obj){
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
	public ResponseEntity<Conduta> update(@PathVariable Integer id, @RequestBody Conduta obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

}
