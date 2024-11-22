package br.edu.senac.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {

  private String nome;
  private String email;
  private String cpf;
  private String telefone;
  private boolean recebeOfertas;
  private List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
  private String senha;

  private boolean status = true;
  private Long generoId;
}
