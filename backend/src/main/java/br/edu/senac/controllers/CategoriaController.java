package br.edu.senac.controllers;

import br.edu.senac.DTO.CategoriaDTO;
import br.edu.senac.models.Categoria;
import br.edu.senac.services.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Categoria")
@RequestMapping("/categoria")
@RestController
public class CategoriaController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity <List<CategoriaDTO>> findAll(){
        List<Categoria> categorias = this.categoriaService.findAll();
            List<CategoriaDTO> categoriaDTO = categorias.stream()
                    .map(categoria -> modelMapper.map(categoria, CategoriaDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(categoriaDTO);
        }
    }

