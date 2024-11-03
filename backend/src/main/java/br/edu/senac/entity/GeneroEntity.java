package br.edu.senac.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "generos")
@Entity(name = "Genero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(30)")
    private String nome;

    @OneToMany(mappedBy = "generoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteEntity> clienteEntity;

}
