package br.edu.senac.controllers;


import br.edu.senac.dto.LoginDTO;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.services.LoginService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Tag(name = "Login")
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<?> post(@RequestBody LoginDTO object) {
        String token = this.loginService.login(object);
        var response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}
