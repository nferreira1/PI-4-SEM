package br.edu.senac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "enderecos")
@Entity(name = "Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O logradouro não pode ser nulo e nem vazio.")
    private String logradouro;

    @Column(nullable = false)
    @NotBlank(message = "O número do endereço não pode ser nulo e nem vazio.")
    private String numero;

    @Column(nullable = true)
    private String complemento;

    @Column(nullable = false)
    @NotBlank(message = "O bairro não pode ser nulo e nem vazio.")
    private String bairro;

    @Column(nullable = false)
    @NotBlank(message = "O CEP não pode ser nulo e nem vazio.")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "A cidade não pode ser nulo e nem vazio.")
    private String cidade;

    @Column(nullable = false)
    @NotBlank(message = "O estado não pode ser nulo e nem vazio.")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
