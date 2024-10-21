package br.edu.senac.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class ProdutoDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String nome;
    private String descricao;
    private int estoque;
    private double valor;
    private boolean status;
    private Long categoriaId;
    private List<ProdutoImagensDTO> imagens;

}
