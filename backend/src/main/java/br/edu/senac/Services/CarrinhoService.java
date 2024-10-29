package br.edu.senac.Services;


import br.edu.senac.Entity.CarrinhoEntity;
import br.edu.senac.Interfaces.ICarrinho;
import br.edu.senac.Pattern.RepositoryGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService extends RepositoryGeneric<CarrinhoEntity, Long> implements ICarrinho {

    public CarrinhoService(JpaRepository<CarrinhoEntity, Long> repository) {
        super(repository);
    }
}
