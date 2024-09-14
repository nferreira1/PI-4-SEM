package br.edu.senac.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome do produto não pode ser nulo e nem vazio.")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "A descrição do produto não pode ser nulo e nem vazia.")
    private String descricao;

    @Column(nullable = false)
    @PositiveOrZero(message = "O estoque deve ser maior ou igual a zero.")
    private int estoque;

    @Column(nullable = false)
    @Positive(message = "O valor deve ser positivo.")
    @DecimalMin(value = "1.00", message = "O valor deve ser no mínimo R$ 1,00.")
    private double valor;

    @Column(nullable = false)
    @NotNull(message = "O status do produto não pode ser nulo.")
    private boolean status = true;

    @Column(nullable = false, columnDefinition = "BYTEA")
    @Lob
    @NotNull(message = "A imagem do produto não pode ser nula.")
    private byte[] imagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoriaEntity;

    @OneToMany(mappedBy = "produtoEntity", cascade = CascadeType.ALL)
    private List<PedidoItensEntity> pedidoItenEntities = new ArrayList<PedidoItensEntity>();

}
