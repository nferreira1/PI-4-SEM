package br.edu.senac.Services;

import br.edu.senac.Exceptions.ErrorResponseException;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Repositories.ClienteRepository;
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

    public ClienteEntity findById(@NonNull Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
        );
    }

    public ClienteEntity criar(@NonNull ClienteEntity clienteEntity) {
        ClienteEntity clienteEntitySalvo = this.clienteRepository.save(clienteEntity);

        if (!clienteEntity.getEnderecoEntities().isEmpty()) {
            clienteEntity.getEnderecoEntities().forEach(endereco -> {
                endereco.setClienteEntity(clienteEntitySalvo);
                this.enderecoService.criar(endereco);
            });
        }

        return clienteEntitySalvo;
    }

}
