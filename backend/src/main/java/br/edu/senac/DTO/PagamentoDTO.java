package br.edu.senac.DTO;


import br.edu.senac.Enum.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoDTO {

    private Long id;
    private Double valor;
    private TipoPagamento tipoPagamento;
}
