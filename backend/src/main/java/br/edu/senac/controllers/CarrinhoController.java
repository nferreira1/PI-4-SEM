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
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Tag(name = "Carrinho")
@RestController
@RequestMapping("/carrinho")
public class CarrinhoController implements IControllerPattern<CarrinhoProdutosDTO, Long> {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<CarrinhoProdutosDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<CarrinhoProdutosDTO> getById(Long id) {
        return null;
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<CarrinhoProdutosResponseDTO>> getByClienteId(@PathVariable Long clienteId) {
        var carrinhoRequest = this.carrinhoService.findByClienteId(clienteId)
                .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));

        List<CarrinhoProdutosResponseDTO> carrinhoResponse = carrinhoRequest.getItens().stream()
                .map(item -> modelMapper.map(item, CarrinhoProdutosResponseDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(carrinhoResponse);
    }


    @Override
    @PostMapping
    public ResponseEntity<CarrinhoProdutosDTO> post(@RequestBody CarrinhoProdutosDTO object) {
        var carrinhoRequest = this.carrinhoService.insert(object);

        if (carrinhoRequest == null) {
            return ResponseEntity.noContent().build();
        }

        this.modelMapper.typeMap(
                CarrinhoProdutosEntity.class,
                CarrinhoProdutosDTO.class
        ).addMappings(mapper -> mapper.map(CarrinhoProdutosEntity::getId, CarrinhoProdutosDTO::setId));

        var carrinhoResponse = this.modelMapper.map(carrinhoRequest, CarrinhoProdutosDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carrinhoRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(carrinhoResponse);
    }

    @Override
    public ResponseEntity<CarrinhoProdutosDTO> put(Long id, CarrinhoProdutosDTO object) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }

}
