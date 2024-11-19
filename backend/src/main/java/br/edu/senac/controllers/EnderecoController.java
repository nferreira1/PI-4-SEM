package br.edu.senac.controllers;

import br.edu.senac.dto.EnderecoDTO;
import br.edu.senac.entity.EnderecoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.services.ClienteService;
import br.edu.senac.services.EnderecoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endereço")
@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<List<EnderecoDTO>> getByToken(JwtAuthenticationToken jwtAuthenticationToken) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));
        var enderecosRequest = cliente.getEnderecos();
        var enderecosResponse = enderecosRequest.stream()
                .map(endereco -> this.modelMapper.map(endereco, EnderecoDTO.class))
                .toList();
        return ResponseEntity.ok().body(enderecosResponse);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<EnderecoDTO> post(JwtAuthenticationToken jwtAuthenticationToken, @RequestBody EnderecoDTO object) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));

        if (cliente.getEnderecos().isEmpty() || !object.isEnderecoPrincipal()) {
            object.setEnderecoPrincipal(true);
        }

        var enderecoRequest = this.modelMapper.map(object, EnderecoEntity.class);
        enderecoRequest.setClienteEntity(cliente);
        var enderecoResponse = this.modelMapper.map(this.enderecoService.insert(enderecoRequest), EnderecoDTO.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enderecoRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoResponse);
    }

    @PutMapping("/{enderecoId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "422", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<EnderecoDTO> put(JwtAuthenticationToken jwtAuthenticationToken, @RequestBody EnderecoDTO object, @PathVariable Long enderecoId) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));
        var enderecoRequest = this.modelMapper.map(object, EnderecoEntity.class);
        enderecoRequest.setClienteEntity(cliente);
        var enderecoResponse = this.modelMapper.map(this.enderecoService.update(enderecoId, enderecoRequest), EnderecoDTO.class);
        return ResponseEntity.ok().body(enderecoResponse);
    }

    @DeleteMapping("/{enderecoId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<EnderecoDTO> delete(JwtAuthenticationToken jwtAuthenticationToken, @PathVariable Long enderecoId) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));

        if (cliente.getEnderecos().stream().noneMatch(e -> e.getId().equals(enderecoId))) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        this.enderecoService.delete(enderecoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<EnderecoDTO> delete() {
        this.enderecoService.delete(92L);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{enderecoId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<EnderecoDTO> patch(JwtAuthenticationToken jwtAuthenticationToken, @PathVariable Long enderecoId) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));

        if (cliente.getEnderecos().stream().noneMatch(e -> e.getId().equals(enderecoId))) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND, "Endereço não encontrado.");
        }

        if (this.enderecoService.findById(enderecoId).isEnderecoPrincipal()) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "Este endereço já é o principal.");
        }

        return ResponseEntity.ok().body(modelMapper.map(this.enderecoService.update(enderecoId), EnderecoDTO.class));
    }

}
