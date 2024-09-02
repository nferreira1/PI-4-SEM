package br.edu.senac.services;

import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.models.Cliente;
import br.edu.senac.repositories.ClienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    public Cliente findById(@NonNull Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
        );
    }

    public Cliente criar(@NonNull Cliente cliente) {
        Cliente clienteSalvo = this.clienteRepository.save(cliente);

        if (!cliente.getEndereco().isEmpty()) {
            cliente.getEndereco().forEach(endereco -> {
                endereco.setCliente(clienteSalvo);
                this.enderecoService.criar(endereco);
            });
        }

        return clienteSalvo;
    }

}
