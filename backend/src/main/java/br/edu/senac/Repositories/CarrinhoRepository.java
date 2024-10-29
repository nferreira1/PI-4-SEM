package br.edu.senac.Repositories;

import br.edu.senac.Entity.CarrinhoEntity;
import br.edu.senac.Entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Long> {

}
