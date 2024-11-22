package br.edu.senac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "carrinhos_produtos")
@Entity(name = "CarrinhoProdutos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoProdutosEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "carrinho_id")
  private CarrinhoEntity carrinho;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id")
  private ProdutoEntity produto;

  @Column(nullable = false)
  private int quantidade;
}
