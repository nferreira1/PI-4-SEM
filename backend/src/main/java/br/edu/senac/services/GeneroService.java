package br.edu.senac.services;

import br.edu.senac.entity.GeneroEntity;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneroService extends ServiceGeneric<GeneroEntity, Long> {

  public GeneroService(JpaRepository<GeneroEntity, Long> repository) {
    super(repository);
  }
}
