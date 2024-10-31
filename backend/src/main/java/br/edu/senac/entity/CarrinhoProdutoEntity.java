package br.edu.senac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "carrinho_produto")
@Entity(name = "CarrinhoProduto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrinho_id", nullable = false)
    private CarrinhoEntity carrinhoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produtoEntity;

    @Column(nullable = false)
    private int quantidade;

    public CarrinhoProdutoEntity(CarrinhoEntity carrinhoEntity, ProdutoEntity produtoEntity, int quantidade) {
        this.carrinhoEntity = carrinhoEntity;
        this.produtoEntity = produtoEntity;
        this.quantidade = quantidade;
    }
}
