package br.edu.senac.Repositories;

import br.edu.senac.Entity.CarrinhoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProdutoEntity,Long> {
}
