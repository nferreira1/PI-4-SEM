package br.edu.senac.services;

import br.edu.senac.dto.CarrinhoDTO;
import br.edu.senac.dto.CarrinhoProdutosResponseDTO;
import br.edu.senac.dto.ClienteDTO;
import br.edu.senac.dto.ProdutoDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutosEntity;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.interfaces.ICarrinho;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.CarrinhoRepository;
import br.edu.senac.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarrinhoService extends ServiceGeneric<CarrinhoEntity, Long> implements ICarrinho {

  @Autowired private CarrinhoRepository carrinhoRepository;

  @Autowired private ProdutoService produtoService;

  @Autowired private ClienteRepository clienteRepository;

  @Autowired private ModelMapper modelMapper;

  public CarrinhoService(JpaRepository<CarrinhoEntity, Long> repository) {
    super(repository);
  }

  public CarrinhoDTO findByCliente(ClienteEntity cliente) {
    var carrinho = this.carrinhoRepository.findByClienteEntity(cliente);

    CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
    carrinhoDTO.setId(carrinho.getId());
    carrinhoDTO.setCliente(this.modelMapper.map(cliente, ClienteDTO.class));

    List<CarrinhoProdutosResponseDTO> itens =
        carrinho.getItens().stream()
            .filter(item -> item.getProduto().getEstoque() >= item.getQuantidade())
            .map(
                item -> {
                  CarrinhoProdutosResponseDTO dto = new CarrinhoProdutosResponseDTO();
                  dto.setId(item.getId());
                  dto.setProduto(this.modelMapper.map(item.getProduto(), ProdutoDTO.class));
                  dto.setQuantidade(item.getQuantidade());
                  return dto;
                })
            .toList();

    carrinhoDTO.setItens(itens);

    return carrinhoDTO;
  }

  @Transactional
  public CarrinhoEntity insert(Long clienteId, Long produtoId, int quantidade) {
    var produto = this.produtoService.findById(produtoId);

    if (produto.getEstoque() < quantidade) {
      throw new ErrorResponseException(
          HttpStatus.BAD_REQUEST,
          "A quantidade máxima em estoque para este produto é de " + produto.getEstoque() + ".");
    }

    if (quantidade <= 0) {
      throw new ErrorResponseException(
          HttpStatus.BAD_REQUEST, "A quantidade deve ser maior do que zero.");
    }

    var cliente =
        this.clienteRepository
            .findById(clienteId)
            .orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    var carrinho = this.carrinhoRepository.findByClienteEntity(cliente);

    carrinho
        .getItens()
        .forEach(
            item -> {
              if ((item.getQuantidade() + quantidade) > item.getProduto().getEstoque()) {
                throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    "A quantidade máxima em estoque para este produto é de "
                        + produto.getEstoque()
                        + ".");
              }
            });

    var itemExistente =
        carrinho.getItens().stream().filter(item -> item.getProduto().equals(produto)).findFirst();

    if (itemExistente.isPresent()) {
      CarrinhoProdutosEntity carrinhoProduto = itemExistente.get();
      carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() + quantidade);
    } else {
      var novoItem = new CarrinhoProdutosEntity();
      novoItem.setCarrinho(carrinho);
      novoItem.setProduto(produto);
      novoItem.setQuantidade(quantidade);
      carrinho.getItens().add(novoItem);
    }

    return this.carrinhoRepository.save(carrinho);
  }

  @Transactional
  public CarrinhoEntity update(Long clienteId, Long produtoId, int quantidade) {
    var produto = this.produtoService.findById(produtoId);

    if (produto.getEstoque() < quantidade) {
      throw new ErrorResponseException(
          HttpStatus.BAD_REQUEST,
          "A quantidade máxima em estoque para este produto é de " + produto.getEstoque() + ".");
    }

    var cliente =
        this.clienteRepository
            .findById(clienteId)
            .orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    var carrinho = this.carrinhoRepository.findByClienteEntity(cliente);

    var itemExistente =
        carrinho.getItens().stream().filter(item -> item.getProduto().equals(produto)).findFirst();

    if (itemExistente.isPresent()) {
      CarrinhoProdutosEntity carrinhoProduto = itemExistente.get();

      if (quantidade == 0) {
        carrinho.getItens().remove(carrinhoProduto);
      } else {
        carrinhoProduto.setQuantidade(quantidade);
      }
    } else {
      throw new ErrorResponseException(
          HttpStatus.BAD_REQUEST, "Este produto não existe no carrinho.");
    }

    return this.carrinhoRepository.save(carrinho);
  }

  @Override
  public List<ProdutoEntity> carregarProdutosDoCarrinho(Long IdCarrinho) {
    CarrinhoEntity carrinho = findById(IdCarrinho);

    if (carrinho == null) {
      new EntityNotFoundException("Carrinho não encontrado");
    }

    List<ProdutoEntity> listProdutos = new ArrayList<>();
    carrinho
        .getItens()
        .forEach(
            carrinhoProduto ->
                listProdutos.add(produtoService.findById(carrinhoProduto.getProduto().getId())));

    if (listProdutos.isEmpty()) {
      new ArrayList<>();
    }

    return listProdutos;
  }
}
