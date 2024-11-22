package br.edu.senac.services;

import br.edu.senac.entity.PagamentoEntity;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService extends ServiceGeneric<PagamentoEntity, Long> {

  public PagamentoService(JpaRepository<PagamentoEntity, Long> repository) {
    super(repository);
  }
}
