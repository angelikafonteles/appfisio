package com.pi2.appfisio.resources;

import java.net.URI;

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

import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.services.EnderecoService;

@RestController
@RequestMapping(value = "enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id) {
		Endereco obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Endereco> insert(@RequestBody Endereco obj){
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
	public ResponseEntity<Endereco> update(@PathVariable Integer id, @RequestBody Endereco obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
