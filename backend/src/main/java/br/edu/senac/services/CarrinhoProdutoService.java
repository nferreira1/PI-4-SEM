package br.edu.senac.services;

import br.edu.senac.entity.CarrinhoProdutoEntity;
import br.edu.senac.interfaces.ICarrinho;
import br.edu.senac.interfaces.ICarrinhoProduto;
import br.edu.senac.interfaces.IProduto;
import br.edu.senac.patterns.ServiceGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoProdutoService extends ServiceGeneric<CarrinhoProdutoEntity, Long> implements ICarrinhoProduto {

    public CarrinhoProdutoService(JpaRepository<CarrinhoProdutoEntity, Long> repository) {
        super(repository);
    }

    @Override
    public void inserirProduto(IProduto carrinho) {
    }

    @Override
    public void inserirCarrinho(ICarrinho carrinho) {
    }
}
