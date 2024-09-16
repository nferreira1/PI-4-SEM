package br.edu.senac.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private int estoque;
    private double valor;
    private boolean status;
    private List<ProdutoImagensDTO> imagens;

}
