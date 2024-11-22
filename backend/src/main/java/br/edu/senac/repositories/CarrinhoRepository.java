package br.edu.senac.repositories;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {

  CarrinhoEntity findByClienteEntity(ClienteEntity object);
}
