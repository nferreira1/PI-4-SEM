package br.edu.senac.interfaces;

import br.edu.senac.entity.CarrinhoProdutoEntity;
import br.edu.senac.patterns.IRepositoryGeneric;

public interface ICarrinhoProduto extends IRepositoryGeneric<CarrinhoProdutoEntity, Long> {
    ;

    void inserirProduto(IProduto produto);

    void inserirCarrinho(ICarrinho carrinho);
}
