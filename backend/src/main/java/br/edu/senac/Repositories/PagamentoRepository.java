package br.edu.senac.Repositories;

import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository <PagamentoEntity, Long> {

}
