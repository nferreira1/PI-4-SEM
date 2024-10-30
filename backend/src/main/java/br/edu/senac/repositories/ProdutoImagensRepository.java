package br.edu.senac.repositories;

import br.edu.senac.entity.ProdutoImagensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoImagensRepository extends JpaRepository<ProdutoImagensEntity, Long> {
}
