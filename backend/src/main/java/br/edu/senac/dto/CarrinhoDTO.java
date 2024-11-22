package br.edu.senac.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarrinhoDTO {

  private Long id;
  private ClienteDTO cliente;
  private List<CarrinhoProdutosResponseDTO> itens;
}
