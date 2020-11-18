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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi2.appfisio.domain.Conduta;
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
	public ResponseEntity<List<Conduta>> findAll(){
		List<Conduta> list = service.findAll();
		return ResponseEntity.ok().body(list);
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
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Conduta conduta, @PathVariable Integer id){
		Conduta obj = service.fromConduta(conduta);
		obj.setId(id);
		obj = service.updateById(obj);
		return ResponseEntity.noContent().build();
	}

}
