package br.edu.senac.controllers;

import br.edu.senac.dto.CarrinhoDTO;
import br.edu.senac.dto.ProdutoDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.interfaces.ICarrinho;
import br.edu.senac.interfaces.IProduto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Carrinho")
@RequestMapping("/carrinho")
@RestController
public class CarrinhoController {

    public IProduto produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICarrinho carrinhoService;

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDTO> getId(@PathVariable Long id) {
        CarrinhoEntity carrinho = carrinhoService.findById(id);

        return ResponseEntity.ok(modelMapper.map(carrinho, CarrinhoDTO.class));

    }

    //Acessando a página pela primeira vez
    @PostMapping
    public ResponseEntity<CarrinhoDTO> post(@RequestBody CarrinhoDTO carrinhoDTO) {

        CarrinhoEntity carrinho = modelMapper.map(carrinhoDTO, CarrinhoEntity.class);

        CarrinhoEntity novoCarrinho = carrinhoService.insert(carrinho);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCarrinho.getId()).toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(novoCarrinho, CarrinhoDTO.class));
    }

    @PostMapping("/{carrinhoId}/produtos")
    public ResponseEntity<CarrinhoDTO> adicionarProduto(@PathVariable Long carrinhoId, @RequestBody ProdutoDTO produtoDTO) {

        ProdutoEntity produtoEntity = modelMapper.map(produtoService.findById(produtoDTO.getId()), ProdutoEntity.class);

        CarrinhoEntity carrinho = carrinhoService.findById(carrinhoId);

        carrinho.adicionarProduto(produtoEntity, carrinho.getQuantidade());

        carrinhoService.update(carrinhoId, carrinho);

        return ResponseEntity.ok(modelMapper.map(carrinho, CarrinhoDTO.class));
    }

    @DeleteMapping("/{carrinhoId}/produtos/{produtoId}")
    public ResponseEntity<Void> removerProduto(@PathVariable Long carrinhoId, @PathVariable Long produtoId) {
        CarrinhoEntity carrinho = carrinhoService.findById(carrinhoId);
        carrinho.removerProduto(produtoId);

        carrinhoService.update(carrinho.getId(), carrinho);

        return ResponseEntity.ok().build();
    }
}

