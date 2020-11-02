package com.pi2.appfisio.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi2.appfisio.domain.Endereco;
import com.pi2.appfisio.services.EnderecoService;

@RestController
@RequestMapping(value = "enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Endereco obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Endereco> createNew(@RequestBody Endereco obj) {
	    Endereco savedObj = service.save(obj);
	    return ResponseEntity.ok(savedObj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Endereco endereco, @PathVariable Integer id){
		Endereco obj = service.fromEndereco(endereco);
		obj.setId(id);
		obj = service.updateById(obj);
		return ResponseEntity.noContent().build();
	}
}
