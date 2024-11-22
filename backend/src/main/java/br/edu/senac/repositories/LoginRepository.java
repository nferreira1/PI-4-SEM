package br.edu.senac.repositories;

import br.edu.senac.entity.LoginEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

  Optional<LoginEntity> findByEmail(String email);
}
