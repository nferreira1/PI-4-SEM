package br.edu.senac.senac.models;

import br.edu.senac.senac.models.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private  String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean status;
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras = new ArrayList<Compra>();



}
