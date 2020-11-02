package com.pi2.appfisio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi2.appfisio.domain.CondutaTecnica;

@Repository
public interface CondutaTecnicaRepository extends JpaRepository<CondutaTecnica, Integer>{

}
