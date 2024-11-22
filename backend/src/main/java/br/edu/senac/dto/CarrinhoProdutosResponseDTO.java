package br.edu.senac.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(
    value = {"id"},
    allowGetters = true)
public class CarrinhoProdutosResponseDTO {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;

  private ProdutoDTO produto;
  private int quantidade;
}
