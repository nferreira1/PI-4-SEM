package br.edu.senac.repositories;

import br.edu.senac.entity.PedidoItensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItensRepository extends JpaRepository<PedidoItensEntity, Long> {
}
