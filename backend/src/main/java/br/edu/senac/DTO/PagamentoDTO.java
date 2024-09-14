package br.edu.senac.DTO;



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
    private int tipoPagamento;
}
