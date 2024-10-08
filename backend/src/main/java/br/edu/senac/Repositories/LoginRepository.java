package br.edu.senac.Repositories;

import br.edu.senac.Entity.LoginEntity;
import br.edu.senac.Entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

}
