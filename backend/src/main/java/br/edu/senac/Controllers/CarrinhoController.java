package br.edu.senac.Controllers;

import br.edu.senac.DTO.CarrinhoDTO;
import br.edu.senac.DTO.CategoriaDTO;
import br.edu.senac.DTO.ClienteDTO;
import br.edu.senac.Entity.CarrinhoEntity;
import br.edu.senac.Entity.CarrinhoProdutoEntity;
import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Exceptions.ErrorResponseException;
import br.edu.senac.Interfaces.ICarrinho;
import br.edu.senac.Interfaces.IProduto;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.CarrinhoService;
import br.edu.senac.Services.CategoriaService;
import br.edu.senac.Services.ProdutoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

