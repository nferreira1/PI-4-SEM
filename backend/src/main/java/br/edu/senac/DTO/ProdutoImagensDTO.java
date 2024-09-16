package br.edu.senac.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoImagensDTO {

    private Long id;
    private String imagem;
    private boolean imagemPrincipal;

}
