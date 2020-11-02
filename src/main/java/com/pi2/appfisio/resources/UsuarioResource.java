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

import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.services.UsuarioService;

@RestController
@RequestMapping(value = "usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Usuario obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> createNew(@RequestBody Usuario obj) {
	    Usuario savedObj = service.save(obj);
	    return ResponseEntity.ok(savedObj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Usuario user, @PathVariable Integer id){
		Usuario obj = service.fromUsuario(user);
		obj.setId(id);
		obj = service.updateById(obj);
		return ResponseEntity.noContent().build();
	}

}
