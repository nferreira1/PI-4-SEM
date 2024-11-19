package br.edu.senac.controllers;

import br.edu.senac.dto.GeneroDTO;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.services.GeneroService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Gênero")
@RequestMapping("/genero")
@RestController
public class GeneroController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GeneroService generoService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<List<GeneroDTO>> getAll() {
        var generosEntities = this.generoService.findAll();
        var generosDtos = generosEntities.stream()
                .map(genero -> modelMapper.map(genero, GeneroDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(generosDtos);
    }

}
