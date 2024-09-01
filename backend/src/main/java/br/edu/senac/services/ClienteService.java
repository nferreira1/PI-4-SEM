package br.edu.senac.services;

import br.edu.senac.models.Cliente;
import br.edu.senac.repositories.ClienteRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(@NonNull Long id) {
        return this.clienteRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Cliente não encontrado.")
        );
    }

    public Cliente criar(@NonNull Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }
}
