package br.edu.senac.services;


import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.interfaces.ICarrinho;
import br.edu.senac.patterns.ServiceGeneric;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService extends ServiceGeneric<CarrinhoEntity, Long> implements ICarrinho {

    @Autowired
    private ProdutoService produtoService;

    public CarrinhoService(JpaRepository<CarrinhoEntity, Long> repository) {
        super(repository);
    }

    @Override
    public void adicionarProduto(Long carrinhoId, Long produtoId, int quantidade) {

        CarrinhoEntity carrinho = findById(carrinhoId);

        ProdutoEntity produto = produtoService.findById(produtoId);

        if (carrinho == null && produto == null) {
            throw new EntityNotFoundException("Carrinho ou Produto não encontrado.");
        }

        carrinho.adicionarProduto(produto, quantidade);
        update(carrinhoId, carrinho);
    }

    @Override
    public void removerProduto(Long carrinhoId, Long produtoId) {

        CarrinhoEntity carrinho = findById(carrinhoId);

        if (carrinho != null) {
            throw new EntityNotFoundException("Carrinho não encontrado.");
        }

        carrinho.removerProduto(produtoId);
        update(carrinho.getId(), carrinho);
    }
}
