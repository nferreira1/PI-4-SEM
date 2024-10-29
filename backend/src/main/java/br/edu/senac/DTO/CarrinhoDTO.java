package br.edu.senac.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class CarrinhoDTO {

    private Long id;
    private int quantidade;
    private ClienteDTO cliente;
}
