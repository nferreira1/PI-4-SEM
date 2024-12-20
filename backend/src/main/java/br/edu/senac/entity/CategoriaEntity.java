package br.edu.senac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "categorias")
@Entity(name = "Categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, message = "A nome da categoria tem que ter no mínimo 3 caracteres.")
    @NotBlank(message = "O nome da categoria não pode ser nulo e nem vazio.")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "O status da categoria não pode ser nulo.")
    private boolean status = true;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "A imagem do produto não pode ser nula.")
    private String imagem;

    @Column(nullable = false)
    @OneToMany(mappedBy = "categoriaEntity")
    private List<ProdutoEntity> produtoEntity = new ArrayList<ProdutoEntity>();

}
