package br.edu.senac.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private List<EnderecoDTO> endereco = new ArrayList<EnderecoDTO>();

}
