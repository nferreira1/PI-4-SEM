package br.edu.senac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "carrinho")
@Entity(name = "Carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer quantidade;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity clienteEntity;

    @OneToMany(mappedBy = "carrinhoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoProdutoEntity> carrinhoProduto;

    public void AdicionarCarrinho(CarrinhoProdutoEntity carrinhoProduto) {
        this.carrinhoProduto.add(carrinhoProduto);
    }
}
