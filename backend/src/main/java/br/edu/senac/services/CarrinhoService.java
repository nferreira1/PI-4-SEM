package br.edu.senac.services;

import br.edu.senac.dto.CarrinhoProdutosDTO;
import br.edu.senac.dto.ClienteDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutosEntity;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.interfaces.ICarrinho;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.CarrinhoProdutosRepository;
import br.edu.senac.repositories.CarrinhoRepository;
import br.edu.senac.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService extends ServiceGeneric<CarrinhoEntity, Long> implements ICarrinho {

    @Autowired
    private CarrinhoProdutosRepository carrinhoProdutosRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteRepository clienteRepository;

    public CarrinhoService(JpaRepository<CarrinhoEntity, Long> repository) {
        super(repository);
    }

    public Optional<CarrinhoEntity> findByClienteId(Long id) {
        return this.carrinhoRepository.findByClienteEntityId(id);
    }

    public CarrinhoProdutosEntity insert(CarrinhoProdutosDTO object) {
        var produto = this.produtoService.findById(object.getProdutoId());

        if (object.getQuantidade() > produto.getEstoque()) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Produto sem estoque.");
        }

        var cliente = this.clienteRepository.findById(object.getClienteId()).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
        );

        var carrinho = this.findByClienteId(cliente.getId()).orElseGet(
                () -> super.insert(new CarrinhoEntity(null, cliente, null))
        );

        var carrinhoProdutosOptional = this.carrinhoProdutosRepository.findByCarrinhoAndProduto(carrinho, produto);

        if (object.getQuantidade() <= 0) {
            carrinhoProdutosOptional.ifPresent(carrinhoProdutos -> {
                this.carrinhoProdutosRepository.delete(carrinhoProdutos);
            });
            return null;
        } else {
            var carrinhoProdutos = carrinhoProdutosOptional.orElse(new CarrinhoProdutosEntity());
            carrinhoProdutos.setCarrinho(carrinho);
            carrinhoProdutos.setProduto(produto);
            carrinhoProdutos.setQuantidade(object.getQuantidade());
            return this.carrinhoProdutosRepository.save(carrinhoProdutos);
        }
    }

    public void delete(CarrinhoProdutosEntity object) {
        this.carrinhoProdutosRepository.delete(object);
    }

    @Override
    public List<ProdutoEntity> carregarProdutosDoCarrinho (Long IdCarrinho) {
        CarrinhoEntity carrinho = findById(IdCarrinho);

        if(carrinho == null)
        {
            new EntityNotFoundException("Carrinho não encontrado");
        }

        List<ProdutoEntity> listProdutos = new ArrayList<>();
        carrinho.getItens().forEach(carrinhoProduto -> listProdutos.add(produtoService.findById(carrinhoProduto.getProduto().getId())));

        if(listProdutos.isEmpty()){
            new ArrayList<>();
        }

        return listProdutos;
    }

    
}
