package br.edu.senac.Interfaces;

import br.edu.senac.Entity.CarrinhoProdutoEntity;
import br.edu.senac.Entity.ProdutoEntity;
import br.edu.senac.Pattern.IRepositoryGeneric;

public interface ICarrinhoProduto extends IRepositoryGeneric<CarrinhoProdutoEntity, Long> {;
    void inserirProduto(IProduto produto);
    void inserirCarrinho(ICarrinho carrinho);
}
