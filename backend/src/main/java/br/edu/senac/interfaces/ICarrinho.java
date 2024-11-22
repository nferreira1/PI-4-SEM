package br.edu.senac.interfaces;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.patterns.IServiceGeneric;
import java.util.List;

public interface ICarrinho extends IServiceGeneric<CarrinhoEntity, Long> {
  List<ProdutoEntity> carregarProdutosDoCarrinho(Long carrinhoId);
}
