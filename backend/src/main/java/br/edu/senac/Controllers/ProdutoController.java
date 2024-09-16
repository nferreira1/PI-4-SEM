package br.edu.senac.Controllers;

import br.edu.senac.DTO.ProdutoDTO;
import br.edu.senac.Entity.ProdutoEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Produto")
@RequestMapping("/produto")
@RestController
public class ProdutoController implements IControllerPattern<ProdutoDTO, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAll() {
        List<ProdutoEntity> produtoEntities = this.produtoService.findAll();
        List<ProdutoDTO> produtoDTOS = produtoEntities.stream().
                map(produto -> modelMapper.map(produto, ProdutoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(produtoDTOS);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProdutoDTO>> getByCategoriaEntityId(@PathVariable Long categoriaId) {
        List<ProdutoEntity> produtoEntities = this.produtoService.findByCategoriaEntityId(categoriaId);
        List<ProdutoDTO> produtoDTOS = produtoEntities.stream().map(produto -> modelMapper.map(produto, ProdutoDTO.class)).toList();
        return ResponseEntity.ok().body(produtoDTOS);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(modelMapper.map(this.produtoService.findById(id), ProdutoDTO.class));
    }

    @Override
    public ResponseEntity<ProdutoDTO> post(ProdutoDTO object) {
        return null;
    }

    @Override
    public ResponseEntity<ProdutoDTO> put(Long id, ProdutoDTO object) {
        return null;
    }
}
