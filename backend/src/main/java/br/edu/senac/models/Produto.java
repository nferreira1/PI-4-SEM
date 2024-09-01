package br.edu.senac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "O nome do produto não pode ser nulo e nem vazio.")
    private String nome;
    @Column(nullable = false)
    @NotBlank(message = "A descrição do produto não pode ser nulo e nem vazia")
    private String descricao;
    @Column(nullable = false)
    @NotBlank(message = "A quantidade do produto não pode ser nulo e nem vazio")
    private int quantidade;
    @Column(nullable = false)
    @NotBlank(message = "O preço do produto não pode ser nulo e nem vazio")
    private double preco;
    @Column(nullable = false)
    @NotBlank(message = "A categoria do produto não pode ser nulo e nem vazio")
    private String categoria;


}
