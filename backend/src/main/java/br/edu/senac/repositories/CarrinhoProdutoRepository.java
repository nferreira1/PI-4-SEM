package br.edu.senac.repositories;

import br.edu.senac.entity.CarrinhoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProdutoEntity, Long> {
}
