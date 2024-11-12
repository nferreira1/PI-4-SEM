package br.edu.senac.controllers;

import br.edu.senac.dto.CarrinhoDTO;
import br.edu.senac.dto.CarrinhoProdutosRequestDTO;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.services.CarrinhoService;
import br.edu.senac.services.ClienteService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Carrinho")
@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<CarrinhoDTO> getByCliente(JwtAuthenticationToken jwtAuthenticationToken) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));
        var carrinho = this.carrinhoService.findByCliente(cliente);
        return ResponseEntity.ok().body(carrinho);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<CarrinhoDTO> post(@RequestBody CarrinhoProdutosRequestDTO object, JwtAuthenticationToken jwtAuthenticationToken) {
        var carrinhoRequest = this.carrinhoService.insert(
                Long.valueOf(jwtAuthenticationToken.getToken().getSubject()),
                object.getProdutoId(),
                object.getQuantidade()
        );
        var carrinhoResponse = this.modelMapper.map(carrinhoRequest, CarrinhoDTO.class);

        return ResponseEntity.ok().body(carrinhoResponse);
    }

    @PatchMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<CarrinhoDTO> patch(@RequestBody CarrinhoProdutosRequestDTO object, JwtAuthenticationToken jwtAuthenticationToken) {
        var carrinhoRequest = this.carrinhoService.update(
                Long.valueOf(jwtAuthenticationToken.getToken().getSubject()),
                object.getProdutoId(),
                object.getQuantidade()
        );
        var carrinhoResponse = this.modelMapper.map(carrinhoRequest, CarrinhoDTO.class);
        return ResponseEntity.ok().body(carrinhoResponse);
    }

}