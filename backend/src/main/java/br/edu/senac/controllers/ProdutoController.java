package br.edu.senac.controllers;

import br.edu.senac.dto.CategoriaDTO;
import br.edu.senac.dto.ProdutoDTO;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.IControllerPattern;
import br.edu.senac.services.CategoriaService;
import br.edu.senac.services.ProdutoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "Produto")
@RequestMapping("/produto")
@RestController
public class ProdutoController implements IControllerPattern<ProdutoDTO, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/categoria/{categoriaId}/produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<List<ProdutoDTO>> getByCategoriaEntityId(@PathVariable Long categoriaId) {
        List<ProdutoEntity> produtoEntities = this.produtoService.findByCategoriaEntityId(categoriaId);
        List<ProdutoDTO> produtoDTOS = produtoEntities.stream().map(produto -> modelMapper.map(produto, ProdutoDTO.class)).toList();
        return ResponseEntity.ok().body(produtoDTOS);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAll() {
        List<ProdutoEntity> produtoEntities = this.produtoService.findAll();
        List<ProdutoDTO> produtoDTOS = produtoEntities.stream().
                map(produto -> modelMapper.map(produto, ProdutoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(produtoDTOS);
    }

    @Override
    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long produtoId) {
        return ResponseEntity.ok().body(modelMapper.map(this.produtoService.findById(produtoId), ProdutoDTO.class));
    }

    @Override
    @PostMapping
    public ResponseEntity<ProdutoDTO> post(@RequestBody ProdutoDTO object) {
        var produtoEntity = modelMapper.map(object, ProdutoEntity.class);
        produtoEntity.setCategoriaEntity(this.categoriaService.findById(object.getCategoriaId()));
        var novoProduto = this.produtoService.insert(produtoEntity);
        var produtoResponse = modelMapper.map(novoProduto, ProdutoDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoResponse);
    }

    @Override
    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoDTO> put(@PathVariable Long produtoId, @RequestBody ProdutoDTO object) {
        var produtoEntity = modelMapper.map(object, ProdutoEntity.class);
        produtoEntity.setCategoriaEntity(this.categoriaService.findById(object.getCategoriaId()));
        var produto = this.produtoService.update(produtoId, modelMapper.map(produtoEntity, ProdutoEntity.class));
        var produtoResponse = modelMapper.map(produto, ProdutoDTO.class);
        return ResponseEntity.ok().body(produtoResponse);
    }

    @PatchMapping("/{produtoId}/status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<CategoriaDTO> patch(@PathVariable Long produtoId) {
        var produto = this.produtoService.update(produtoId);
        var produtoResponse = modelMapper.map(produto, CategoriaDTO.class);
        return ResponseEntity.ok().body(produtoResponse);
    }

    @Override
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable Long produtoId) {
        this.produtoService.delete(produtoId);
        return ResponseEntity.noContent().build();
    }

}
