package br.edu.senac.services;

import br.edu.senac.entity.PedidoItensEntity;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoItensService extends ServiceGeneric<PedidoItensEntity, Long> {

    public PedidoItensService(JpaRepository<PedidoItensEntity, Long> repository) {
        super(repository);
    }
    
}
