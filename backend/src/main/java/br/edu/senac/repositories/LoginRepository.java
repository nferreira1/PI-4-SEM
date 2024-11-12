package br.edu.senac.repositories;

import br.edu.senac.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    Optional<LoginEntity> findByEmailAndClienteEntityStatusTrue(String email);

}
