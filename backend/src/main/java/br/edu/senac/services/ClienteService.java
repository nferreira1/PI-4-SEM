package br.edu.senac.services;

import br.edu.senac.models.Cliente;
import br.edu.senac.repositories.ClienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> findById(@NonNull Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente criar(@NonNull Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
