package br.edu.senac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, message = "O nome do cliente tem que ter no mínimo 3 caracteres.")
    @NotBlank(message = "O nome não pode ser nulo e nem vazio.")
    private String nome;

    @Column(nullable = false, unique = true)
    @Email(message = "O e-mail é deve ter um formato válido.")
    @NotBlank(message = "O e-mail não pode ser nulo e nem vazio.")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter no mínimo 11 caracteres.")
    @NotBlank(message = "O CPF não pode ser nulo e nem vazio.")
    private String cpf;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false)
    @NotBlank(message = "O telefone não pode ser nulo e nem vazio.")
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco = new ArrayList<Endereco>();

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compra = new ArrayList<Compra>();

}
