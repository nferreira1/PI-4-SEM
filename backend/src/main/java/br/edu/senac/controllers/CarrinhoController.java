package br.edu.senac.controllers;

import br.edu.senac.dto.CarrinhoDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutoEntity;
import br.edu.senac.interfaces.ICarrinho;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ICarrinho carrinhoService;

    @GetMapping(("/{id}"))
    public ResponseEntity<CarrinhoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(modelMapper
                        .map(this.carrinhoService.findById(id), CarrinhoDTO.class));
    }

    @PostMapping
    public ResponseEntity<CarrinhoDTO> post(CarrinhoDTO object) {
        var carrinho = modelMapper.map(object, CarrinhoEntity.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carrinho
                        .getId()).toUri();

        CarrinhoProdutoEntity n1 = new CarrinhoProdutoEntity();
        carrinho.AdicionarCarrinho(n1);

        this.carrinhoService.insert(carrinho);
        return ResponseEntity.created(uri).body(modelMapper.map(carrinho, CarrinhoDTO.class));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}

