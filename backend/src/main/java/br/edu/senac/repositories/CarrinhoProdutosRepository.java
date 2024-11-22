package br.edu.senac.repositories;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutosEntity;
import br.edu.senac.entity.ProdutoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoProdutosRepository extends JpaRepository<CarrinhoProdutosEntity, Long> {

  Optional<CarrinhoProdutosEntity> findByCarrinhoAndProduto(
      CarrinhoEntity carrinho, ProdutoEntity produto);
}
