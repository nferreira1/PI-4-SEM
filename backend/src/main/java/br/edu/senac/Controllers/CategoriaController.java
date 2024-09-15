package br.edu.senac.Controllers;

import br.edu.senac.DTO.CategoriaDTO;
import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Services.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Categoria")
@RequestMapping("/categoria")
@RestController
public class CategoriaController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaEntity> categoriaEntities = this.categoriaService.findAll();
        List<CategoriaDTO> categoriaDTO = categoriaEntities.stream()
                .map(categoriaEntity -> modelMapper.map(categoriaEntity, CategoriaDTO.class))
                .toList();
        return ResponseEntity.ok().body(categoriaDTO);
    }

}

