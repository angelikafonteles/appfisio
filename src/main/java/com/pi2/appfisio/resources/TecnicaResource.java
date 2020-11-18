package com.pi2.appfisio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi2.appfisio.domain.Tecnica;
import com.pi2.appfisio.services.TecnicaService;

@RestController
@RequestMapping(value = "tecnicas")
public class TecnicaResource {
	
	@Autowired
	private TecnicaService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Tecnica> findById(@PathVariable Integer id) {
		Tecnica obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Tecnica>> findAll(){
		List<Tecnica> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
