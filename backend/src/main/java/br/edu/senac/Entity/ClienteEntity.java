package br.edu.senac.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {

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
    @NotNull(message = "O status do cliente não pode ser nulo.")
    private Boolean status = true;

    @Column(nullable = false)
    @NotBlank(message = "O telefone não pode ser nulo e nem vazio.")
    private String telefone;

    @OneToMany(mappedBy = "clienteEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> enderecoEntities = new ArrayList<EnderecoEntity>();


    @OneToMany(mappedBy = "clienteEntity")
    private List<PedidoEntity> pedidoEntities = new ArrayList<PedidoEntity>();

    @JsonIgnore
    @OneToOne(mappedBy = "cliente")
    private LoginEntity loginEntity;

}
