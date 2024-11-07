package br.edu.senac.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarrinhoDTO {

    private Long id;
    private ClienteDTO cliente;
    private List<CarrinhoProdutosDTO> itens;

}
