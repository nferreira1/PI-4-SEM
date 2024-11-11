package br.edu.senac.controllers;


import br.edu.senac.dto.CategoriaDTO;
import br.edu.senac.dto.LoginDTO;
import br.edu.senac.entity.CategoriaEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.services.GeneroService;
import br.edu.senac.services.LoginService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Login")
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LoginService loginService;


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<LoginDTO> post(@RequestBody LoginDTO object) {
            loginService.fazendoLogin(object);
            return ResponseEntity.ok().body(null);
    }
}
