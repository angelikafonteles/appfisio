package com.pi2.appfisio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi2.appfisio.domain.Login;
import com.pi2.appfisio.domain.Usuario;
import com.pi2.appfisio.repositories.UsuarioRepository;
import com.pi2.appfisio.services.exceptios.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario insert(Usuario obj){
        return repo.save(obj);
    }
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public Usuario updateById(Usuario obj) {
		Usuario newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setOrgaoDeClasse(obj.getOrgaoDeClasse());
	
	}
	
	public Usuario fromUsuario(Usuario obj) {
		return new Usuario(obj.getId(), obj.getNome(), obj.getCpf(), obj.getDataNascimento(), obj.getOrgaoDeClasse(), new Login());
	}
		
}
