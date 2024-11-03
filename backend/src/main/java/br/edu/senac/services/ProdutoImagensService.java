package br.edu.senac.services;

import br.edu.senac.entity.ProdutoImagensEntity;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoImagensService extends ServiceGeneric<ProdutoImagensEntity, Long> {

    public ProdutoImagensService(JpaRepository<ProdutoImagensEntity, Long> repository) {
        super(repository);
    }
    
}
