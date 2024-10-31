package br.edu.senac.interfaces;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutoEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.patterns.IServiceGeneric;

public interface ICarrinho extends IServiceGeneric<CarrinhoEntity, Long> {

    void  adicionarProduto(Long carrinhoId, Long produtoId, int quantidade);
    void  removerProduto(Long carrinhoId, Long carrinhoProdutoId);
}
