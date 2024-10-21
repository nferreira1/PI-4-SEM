package br.edu.senac.Controllers;

import br.edu.senac.DTO.CategoriaDTO;
import br.edu.senac.DTO.ProdutoDTO;
import br.edu.senac.Entity.ProdutoEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.CategoriaService;
import br.edu.senac.Services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAll() {
        List<ProdutoEntity> produtoEntities = this.produtoService.findAll();
        List<ProdutoDTO> produtoDTOS = produtoEntities.stream().
                map(produto -> modelMapper.map(produto, ProdutoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(produtoDTOS);
    }

    @GetMapping("/categoria/{categoriaId}/produtos")
    public ResponseEntity<List<ProdutoDTO>> getByCategoriaEntityId(@PathVariable Long categoriaId) {
        List<ProdutoEntity> produtoEntities = this.produtoService.findByCategoriaEntityId(categoriaId);
        List<ProdutoDTO> produtoDTOS = produtoEntities.stream().map(produto -> modelMapper.map(produto, ProdutoDTO.class)).toList();
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
        return ResponseEntity.ok().body(produtoResponse);
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
    public ResponseEntity<CategoriaDTO> patch(@PathVariable Long produtoId) {
        var produto = this.produtoService.update(produtoId);
        var produtoResponse = modelMapper.map(produto, CategoriaDTO.class);
        return ResponseEntity.ok().body(produtoResponse);
    }

    @Override
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long produtoId) {
        this.produtoService.delete(produtoId);
        return ResponseEntity.noContent().build();
    }

}
