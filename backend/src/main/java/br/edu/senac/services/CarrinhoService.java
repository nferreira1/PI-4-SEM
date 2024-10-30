package br.edu.senac.services;


import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.interfaces.ICarrinho;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService extends ServiceGeneric<CarrinhoEntity, Long> implements ICarrinho {

    public CarrinhoService(JpaRepository<CarrinhoEntity, Long> repository) {
        super(repository);
    }
}
