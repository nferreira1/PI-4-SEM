package br.edu.senac.Repositories;

import br.edu.senac.Entity.ProdutoImagensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoImagensRepository extends JpaRepository<ProdutoImagensEntity, Long> {
}
