package br.edu.senac.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "login")
@Entity(name = "Login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "O endereço email não pode ser nulo.")
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "A senha não pode ser nulo.")
    private String senha;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private ClienteEntity clienteEntity;

}
