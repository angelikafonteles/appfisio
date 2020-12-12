package com.pi2.appfisio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pi2.appfisio.domain.Sessao;
import com.pi2.appfisio.dto.SessaoDTO;
import com.pi2.appfisio.services.SessaoService;

@RestController
@RequestMapping(value = "sessoes")
public class SessaoResource {
	
	@Autowired
	private SessaoService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Sessao> findById(@PathVariable Integer id) {
		Sessao obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<SessaoDTO>> findAll(){
		List<Sessao> list = service.findAll();
		List<SessaoDTO> listDto = list.stream().map(obj -> new SessaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Sessao> insert(@RequestBody Sessao obj){
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
	public ResponseEntity<Sessao> update(@PathVariable Integer id, @RequestBody Sessao obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<SessaoDTO>> findPage(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "nome")String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC")String direction) {
		Page<Sessao> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<SessaoDTO> listDto = list.map(obj -> new SessaoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
