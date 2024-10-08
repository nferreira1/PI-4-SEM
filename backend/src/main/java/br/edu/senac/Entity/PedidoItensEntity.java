package br.edu.senac.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pedidos_itens")
@Entity(name = "PedidoItens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedidoEntity;

    @Column(nullable = false)
    @NotBlank(message = "O nome do produto não pode ser nulo e nem vazio.")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "A descrição do produto não pode ser nulo e nem vazia.")
    private String descricao;

    @Column(nullable = false)
    @Positive(message = "A quantidade deve ser maior que zero.")
    private int quantidade;

    @Column(nullable = false)
    @Positive(message = "O valor deve ser um valor positivo.")
    @DecimalMin(value = "1.00", message = "O valor deve ser no mínimo R$ 1,00.")
    private double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProdutoEntity produtoEntity;

}
