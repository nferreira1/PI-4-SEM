package br.edu.senac.services;

import br.edu.senac.models.Endereco;
import br.edu.senac.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco criar(Endereco endereco) {
        return this.enderecoRepository.save(endereco);
    }

}
