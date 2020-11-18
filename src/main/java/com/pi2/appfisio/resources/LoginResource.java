package com.pi2.appfisio.resources;

import java.net.URI;

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

import com.pi2.appfisio.domain.Login;
import com.pi2.appfisio.services.LoginService;

@RestController
@RequestMapping(value = "logins")
public class LoginResource {

	@Autowired
	private LoginService service;

	@GetMapping(value="/{id}")
	public ResponseEntity<Login> findById(@PathVariable Integer id) {
		Login obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{email}/{senha}")
	public ResponseEntity<Login> findByLoginPass(@PathVariable String email, @PathVariable String senha) {
		Login obj = service.findByLoginPass(email, senha);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Login> insert(@RequestBody Login obj){
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
