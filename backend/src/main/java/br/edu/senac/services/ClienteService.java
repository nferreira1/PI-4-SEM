package br.edu.senac.services;

import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.IServiceGeneric;
import br.edu.senac.repositories.ClienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IServiceGeneric<ClienteEntity, Long> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private LoginService loginService;

    @Override
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity findById(@NonNull Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
        );
    }

    @Override
    public ClienteEntity insert(ClienteEntity object) {
        ClienteEntity clienteEntitySalvo = this.clienteRepository.save(object);

        if (!object.getEnderecoEntities().isEmpty()) {
            object.getEnderecoEntities().forEach(endereco -> {
                endereco.setClienteEntity(clienteEntitySalvo);
                this.enderecoService.insert(endereco);
            });
        }

        return clienteEntitySalvo;
    }

    @Override
    public ClienteEntity update(Long id, ClienteEntity object) {
        return null;
    }

    public ClienteEntity update(Long id, Boolean status) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}