package br.edu.senac.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, message = "O nome do endereço tem que ter no mínimo 3 caracteres.")
    @NotBlank(message = "O nome do endereço não pode ser nulo e nem vazio.")
    private String nomeEndereco;

    @Column(nullable = false)
    @NotNull(message = "O endereço principal não pode ser nulo.")
    private boolean enderecoPrincipal;

    @Column(nullable = false)
    @NotBlank(message = "O logradouro não pode ser nulo e nem vazio.")
    private String logradouro;

    @Column(nullable = false)
    @NotNull(message = "O número do endereço não pode ser nulo.")
    @Min(value = 1, message = "O número do endereço deve ser maior do que zero.")
    private int numero;

    @Column(nullable = true)
    private String complemento;

    @Column(nullable = false)
    @NotBlank(message = "O bairro não pode ser nulo e nem vazio.")
    private String bairro;

    @Column(nullable = false, columnDefinition = "CHAR(8)", length = 8)
    @NotBlank(message = "O CEP não pode ser nulo e nem vazio.")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "A cidade não pode ser nulo e nem vazio.")
    private String cidade;

    @Column(nullable = false, columnDefinition = "CHAR(2)", length = 2)
    @NotBlank(message = "O estado não pode ser nulo e nem vazio.")
    @Size(min = 2, max = 2, message = "O estado deve ter no mínimo e no máximo 2 caracteres.")
    private String UF;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity clienteEntity;

}
