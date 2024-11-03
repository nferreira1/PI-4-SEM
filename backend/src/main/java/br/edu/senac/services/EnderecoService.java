package br.edu.senac.services;

import br.edu.senac.entity.EnderecoEntity;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService extends ServiceGeneric<EnderecoEntity, Long> {

    public EnderecoService(JpaRepository<EnderecoEntity, Long> repository) {
        super(repository);
    }
    
}
