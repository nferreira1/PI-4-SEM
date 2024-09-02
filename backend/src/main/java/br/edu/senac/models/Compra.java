package br.edu.senac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "compras")
@Entity(name = "Compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false) // Essa compra vai ter obrigatoriamente um ID do cliente
    @NotNull(message = "O ID do cliente não pode ser nulo.")
    private Cliente cliente;

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "compras_produtos", joinColumns = @JoinColumn(name = "compra_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "produto_id", nullable = false))
    private List<Produto> produtos;

    @Column(nullable = false)
    private LocalDateTime dataCompra;

}
