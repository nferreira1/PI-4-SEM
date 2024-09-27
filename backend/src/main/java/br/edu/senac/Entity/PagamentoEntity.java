package br.edu.senac.Entity;

import br.edu.senac.Enum.StatusPagamento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Table(name = "Pagamento")
@Entity(name = "Pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "A data do pagamento não pode ser nulo.")
    private Date dataPagamento;

    @Column(nullable = false)
    @NotNull(message = "O valor do pagamento não pode ser Nulo")
    private Double valorPagamento;

    @OneToMany(mappedBy = "pagamentoEntity", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidoEntity;

    @Column(nullable = false)
    @NotNull(message = "O status do pagamento não pode ser Nulo")
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

}
