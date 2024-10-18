package br.edu.senac.Controllers;

import br.edu.senac.DTO.CategoriaDTO;
import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categoria")
@RequestMapping("/categoria")
@RestController
public class CategoriaController implements IControllerPattern<CategoriaDTO, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAll() {
        List<CategoriaEntity> categoriaEntities = this.categoriaService.findAll();
        List<CategoriaDTO> categoriaDTO = categoriaEntities.stream()
                .map(categoriaEntity -> modelMapper.map(categoriaEntity, CategoriaDTO.class))
                .toList();
        return ResponseEntity.ok().body(categoriaDTO);
    }

    @Override
    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDTO> getById(@PathVariable Long categoriaId) {
        var categoria = this.categoriaService.findById(categoriaId);
        var categoriaResponse = modelMapper.map(categoria, CategoriaDTO.class);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @Override
    @PostMapping
    public ResponseEntity<CategoriaDTO> post(@RequestBody CategoriaDTO object) {
        var novaCategoria = this.categoriaService.insert(modelMapper.map(object, CategoriaEntity.class));
        var categoriaResponse = modelMapper.map(novaCategoria, CategoriaDTO.class);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @Override
    @PutMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDTO> put(@PathVariable Long categoriaId, @RequestBody CategoriaDTO object) {
        var categoria = this.categoriaService.update(categoriaId, modelMapper.map(object, CategoriaEntity.class));
        var categoriaResponse = modelMapper.map(categoria, CategoriaDTO.class);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @PatchMapping("/{categoriaId}/status")
    public ResponseEntity<CategoriaDTO> patch(@PathVariable Long categoriaId) {
        var categoria = this.categoriaService.update(categoriaId);
        var categoriaResponse = modelMapper.map(categoria, CategoriaDTO.class);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @Override
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long categoriaId) {
        this.categoriaService.delete(categoriaId);
        return ResponseEntity.noContent().build();
    }

}

