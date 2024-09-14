package br.edu.senac.Services;

import br.edu.senac.Entity.EnderecoEntity;
import br.edu.senac.Repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoEntity criar(EnderecoEntity enderecoEntity) {
        return this.enderecoRepository.save(enderecoEntity);
    }

}
