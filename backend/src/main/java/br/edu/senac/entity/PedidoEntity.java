package br.edu.senac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@Setter
@NoArgsConstructor
public class PedidoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime dataCompra;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id", nullable = false)
  @NotNull(message = "O ID do cliente não pode ser nulo.")
  private ClienteEntity clienteEntity;

  @OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
  private List<PedidoItensEntity> pedidosItens = new ArrayList<PedidoItensEntity>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pagamento_id", nullable = false)
  private PagamentoEntity pagamentoEntity;

  public PedidoEntity(
      Long id,
      LocalDateTime dataCompra,
      ClienteEntity clienteEntity,
      PagamentoEntity pagamentoEntity) {
    this.id = id;
    this.dataCompra = dataCompra;
    this.clienteEntity = clienteEntity;
    this.pagamentoEntity = pagamentoEntity;
  }

  public void adicionarPedidoItem(PedidoItensEntity pedidoItens) {
    pedidosItens.add(pedidoItens);
  }
}
