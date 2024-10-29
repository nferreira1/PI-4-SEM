package br.edu.senac.Services;

import br.edu.senac.Entity.CarrinhoEntity;
import br.edu.senac.Entity.CarrinhoProdutoEntity;
import br.edu.senac.Interfaces.ICarrinho;
import br.edu.senac.Interfaces.ICarrinhoProduto;
import br.edu.senac.Interfaces.IProduto;
import br.edu.senac.Pattern.RepositoryGeneric;
import br.edu.senac.Repositories.CarrinhoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoProdutoService extends RepositoryGeneric<CarrinhoProdutoEntity, Long> implements ICarrinhoProduto {


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
