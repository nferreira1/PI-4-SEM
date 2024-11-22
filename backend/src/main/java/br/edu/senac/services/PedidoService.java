package br.edu.senac.services;

import br.edu.senac.entity.*;
import br.edu.senac.interfaces.IPedido;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService extends ServiceGeneric<PedidoEntity, Long> implements IPedido {

  private final PedidoRepository pedidoRepository;
  @Autowired private final ClienteService clienteService;

  @Autowired
  public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService) {
    super(pedidoRepository);
    this.pedidoRepository = pedidoRepository;
    this.clienteService = clienteService;
  }

  @Transactional(readOnly = true)
  public void gerarPedidoItem(PedidoEntity pedido, CarrinhoEntity carrinhoEntity) {

    carrinhoEntity
        .getItens()
        .forEach(
            item -> {
              double valor = item.getQuantidade() * item.getProduto().getValor();
              PedidoItensEntity pedidoItens =
                  new PedidoItensEntity(
                      null,
                      item.getProduto().getNome(),
                      item.getProduto().getDescricao(),
                      item.getQuantidade(),
                      valor,
                      item.getProduto(),
                      pedido);
              pedido.adicionarPedidoItem(pedidoItens);
              item.getProduto().adicionarPedidoItensEntity(pedidoItens);
            });
  }
}
