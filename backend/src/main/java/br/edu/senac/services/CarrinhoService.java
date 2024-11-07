package br.edu.senac.services;

import br.edu.senac.dto.CarrinhoProdutosDTO;
import br.edu.senac.dto.ClienteDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutosEntity;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.CarrinhoProdutosRepository;
import br.edu.senac.repositories.CarrinhoRepository;
import br.edu.senac.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService extends ServiceGeneric<CarrinhoEntity, Long> {

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
        var cliente = this.clienteRepository.findById(object.getClienteId()).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
        );

        var carrinho = this.findByClienteId(cliente.getId()).orElseGet(
                () -> super.insert(new CarrinhoEntity(null, cliente, null))
        );

        var carrinhoProdutosOptional = this.carrinhoProdutosRepository.findByCarrinhoAndProduto(carrinho, produto);

        if (object.getQuantidade() <= 0) {
            carrinhoProdutosOptional.ifPresent(carrinhoProdutos -> this.carrinhoProdutosRepository.delete(carrinhoProdutos));
            return null;
        } else {
            var carrinhoProdutos = carrinhoProdutosOptional.orElse(new CarrinhoProdutosEntity());
            carrinhoProdutos.setCarrinho(carrinho);
            carrinhoProdutos.setProduto(produto);
            carrinhoProdutos.setQuantidade(object.getQuantidade());
            return this.carrinhoProdutosRepository.save(carrinhoProdutos);
        }
    }

    
}
