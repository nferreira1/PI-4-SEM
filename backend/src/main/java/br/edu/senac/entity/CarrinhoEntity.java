package br.edu.senac.entity;

import br.edu.senac.designpattern.CarrinhoProdutoFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity clienteEntity;

    @OneToMany(mappedBy = "carrinhoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoProdutoEntity> carrinhoProduto;


    public void adicionarProduto(ProdutoEntity produto, int quantidade) {
        CarrinhoProdutoEntity carrinhoProdutoEntity = CarrinhoProdutoFactory.createCarrinhoProduto(this, produto, quantidade);
        this.carrinhoProduto.add(carrinhoProdutoEntity);
    }

    public void removerProduto(Long produtoId) {
        this.carrinhoProduto.removeIf(c-> c.getProdutoEntity().getId() == produtoId);
    }
}
