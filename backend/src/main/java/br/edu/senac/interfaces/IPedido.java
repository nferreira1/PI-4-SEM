package br.edu.senac.interfaces;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.PedidoEntity;
import br.edu.senac.patterns.IServiceGeneric;

public interface IPedido extends IServiceGeneric<PedidoEntity, Long> {
  void gerarPedidoItem(PedidoEntity pedido, CarrinhoEntity carrinhoEntity);
}
