package br.edu.senac.repositories;

import br.edu.senac.entity.ProdutoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

  List<ProdutoEntity> findByCategoriaEntityId(Long id);
}
