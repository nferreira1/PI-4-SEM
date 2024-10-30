package br.edu.senac.interfaces;

import br.edu.senac.entity.CarrinhoProdutoEntity;
import br.edu.senac.patterns.IServiceGeneric;

public interface ICarrinhoProduto extends IServiceGeneric<CarrinhoProdutoEntity, Long> {

    void inserirProduto(IProduto produto);

    void inserirCarrinho(ICarrinho carrinho);

}
