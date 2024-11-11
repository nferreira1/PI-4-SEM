package br.edu.senac.controllers;

import br.edu.senac.dto.CarrinhoDTO;
import br.edu.senac.dto.CarrinhoProdutosDTO;
import br.edu.senac.dto.CarrinhoProdutosResponseDTO;
import br.edu.senac.dto.CategoriaDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutosEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.IControllerPattern;
import br.edu.senac.services.CarrinhoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Tag(name = "Carrinho")
@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<CarrinhoProdutosResponseDTO>> getByClienteId(@PathVariable Long clienteId) {
        var carrinhoRequest = this.carrinhoService.findByClienteId(clienteId)
                .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));

        List<CarrinhoProdutosResponseDTO> carrinhoResponse = carrinhoRequest.getItens().stream()
                .filter(item -> {
                    boolean temEstoque = item.getProduto().getEstoque() > item.getQuantidade();
                    this.carrinhoService.delete(item);
                    if (!temEstoque) {
                        item.setQuantidade(0);
                        var carrinhoProdutosDTO = this.modelMapper.map(item, CarrinhoProdutosDTO.class);
                        this.carrinhoService.insert(carrinhoProdutosDTO);
                    }
                    return temEstoque;
                })
                .map(item -> modelMapper.map(item, CarrinhoProdutosResponseDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(carrinhoResponse);
    }

    @PostMapping
    public ResponseEntity<CarrinhoProdutosResponseDTO> post(@RequestBody CarrinhoProdutosDTO object) {
        var carrinhoRequest = this.carrinhoService.insert(object);

        if (carrinhoRequest == null) {
            return ResponseEntity.noContent().build();
        }

        this.modelMapper.typeMap(
                CarrinhoProdutosEntity.class,
                CarrinhoProdutosDTO.class
        ).addMappings(mapper -> mapper.map(CarrinhoProdutosEntity::getId, CarrinhoProdutosDTO::setId));

        var carrinhoResponse = this.modelMapper.map(carrinhoRequest, CarrinhoProdutosResponseDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carrinhoRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(carrinhoResponse);
    }

}