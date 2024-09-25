package br.edu.senac.DTO;


import br.edu.senac.Entity.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {

    private String email;
    private String senha;
    private ClienteEntity cliente;
}
