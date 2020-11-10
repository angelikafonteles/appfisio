package com.pi2.appfisio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.pi2.appfisio.domain.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    @Query(value = "select * from login where email = ?1 and senha = ?2", nativeQuery = true)
    Optional<Login> findByLoginSenha(String email, String senha);
    
}
