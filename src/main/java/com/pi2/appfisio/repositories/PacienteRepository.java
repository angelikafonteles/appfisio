package com.pi2.appfisio.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pi2.appfisio.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	@Transactional(readOnly=true)
	Paciente findByEmail(String email);
	
	@Transactional(readOnly=true)
	Optional<Paciente> findByCpf(String cpf);
	
	@Transactional(readOnly=true)
	Optional<Paciente> findByNome(String nome);
}
