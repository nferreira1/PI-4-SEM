package br.edu.senac.services;

import br.edu.senac.entity.PedidoEntity;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends ServiceGeneric<PedidoEntity, Long> {

    public PedidoService(JpaRepository<PedidoEntity, Long> repository) {
        super(repository);
    }
    
}
