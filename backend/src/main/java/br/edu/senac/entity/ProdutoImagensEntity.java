package br.edu.senac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produto_imagens")
@Entity(name = "ProdutoImagens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoImagensEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "TEXT")
  @NotBlank(message = "A imagem não pode ser nula e nem vazia.")
  private String imagem;

  @Column(nullable = false)
  @NotNull(message = "A imagem principal não pode ser nula.")
  private boolean imagemPrincipal;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id", nullable = false)
  private ProdutoEntity produtoEntity;
}
