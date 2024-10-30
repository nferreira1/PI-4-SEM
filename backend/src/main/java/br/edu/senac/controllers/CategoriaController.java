package br.edu.senac.controllers;

import br.edu.senac.dto.CategoriaDTO;
import br.edu.senac.entity.CategoriaEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.IControllerPattern;
import br.edu.senac.services.CategoriaService;
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
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(categoriaResponse);
    }

    @Override
    @PutMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDTO> put(@PathVariable Long categoriaId, @RequestBody CategoriaDTO object) {
        var categoria = this.categoriaService.update(categoriaId, modelMapper.map(object, CategoriaEntity.class));
        var categoriaResponse = modelMapper.map(categoria, CategoriaDTO.class);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @PatchMapping("/{categoriaId}/status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<CategoriaDTO> patch(@PathVariable Long categoriaId) {
        var categoria = this.categoriaService.update(categoriaId);
        var categoriaResponse = modelMapper.map(categoria, CategoriaDTO.class);
        return ResponseEntity.ok().body(categoriaResponse);
    }

    @Override
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> delete(@PathVariable Long categoriaId) {
        this.categoriaService.delete(categoriaId);
        return ResponseEntity.noContent().build();
    }

}

