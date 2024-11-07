package br.edu.senac.services;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.PedidoEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.interfaces.IPedido;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService extends ServiceGeneric<PedidoEntity, Long> implements IPedido {


    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        super(pedidoRepository);
        this.pedidoRepository = pedidoRepository;
    }

//Arrumar
/*@Transactional(readOnly = true)
    @Override
    public void gerarPedidoItem(Long idPedido, List<ProdutoEntity> produtos, CarrinhoEntity carrinhoEntity) {
        double valorTotal = p.getValor() * carrinhoEntity.getItens().forEach(p -> p.getQuantidade());
        for(var p : produtos){

            pedidoRepository.gerarPedidoItem(p.getNome(), p.getDescricao(),carrinhoEntity.getQuantidade(), valorTotal , idPedido, p.getId());
        }
    }*/


    @Override
    public List<ProdutoEntity> consultarProdutosPedido(Long idPedido) {
      //Fazer: Otavio
        return List.of();
    }

}
