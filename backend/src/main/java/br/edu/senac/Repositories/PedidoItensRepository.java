package br.edu.senac.Repositories;

import br.edu.senac.Entity.PedidoEntity;
import br.edu.senac.Entity.PedidoItensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoItensRepository extends JpaRepository<PedidoItensEntity, Long> {
}
