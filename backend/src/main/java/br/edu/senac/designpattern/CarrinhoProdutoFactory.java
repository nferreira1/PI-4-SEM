package br.edu.senac.designpattern;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutoEntity;
import br.edu.senac.entity.ProdutoEntity;

public class CarrinhoProdutoFactory {
    public static CarrinhoProdutoEntity createCarrinhoProduto(CarrinhoEntity carrinho, ProdutoEntity produto, int quantidade) {
        return new CarrinhoProdutoEntity(carrinho, produto, quantidade);
    }
}
