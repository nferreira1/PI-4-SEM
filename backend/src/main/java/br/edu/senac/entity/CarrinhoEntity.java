package br.edu.senac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "carrinhos")
@Entity(name = "Carrinho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity clienteEntity;

    @OneToMany(mappedBy = "carrinho", orphanRemoval = true)
    private List<CarrinhoProdutosEntity> itens;

}
