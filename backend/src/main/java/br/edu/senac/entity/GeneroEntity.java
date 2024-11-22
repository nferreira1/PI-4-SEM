package br.edu.senac.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @JsonBackReference
  private List<ClienteEntity> clienteEntity;
}
