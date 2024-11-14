package br.edu.senac.controllers;

import br.edu.senac.dto.ClienteDTO;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.IControllerPattern;
import br.edu.senac.services.CarrinhoService;
import br.edu.senac.services.ClienteService;
import br.edu.senac.services.GeneroService;
import br.edu.senac.services.LoginService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Cliente")
@RequestMapping("/cliente")
@RestController
public class ClienteController implements IControllerPattern<ClienteDTO, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private LoginService loginService;

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<ClienteEntity> clienteEntities = this.clienteService.findAll();
        List<ClienteDTO> clienteDTOS = clienteEntities.stream().
                map(cliente -> this.modelMapper.map(cliente, ClienteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @Override
    public ResponseEntity<ClienteDTO> getById(Long id) {
        return null;
    }

    @GetMapping("/me")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<ClienteDTO> getByToken(JwtAuthenticationToken jwtAuthenticationToken) {
        var cliente = this.clienteService.findById(Long.valueOf(jwtAuthenticationToken.getToken().getSubject()));
        var clienteResponse = this.modelMapper.map(cliente, ClienteDTO.class);
        return ResponseEntity.ok().body(clienteResponse);
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteDTO> post(@RequestBody ClienteDTO object) {
        this.generoService.findById(object.getGeneroId());
        var cliente = this.clienteService.insert(this.modelMapper.map(object, ClienteEntity.class));
        this.loginService.insert(cliente, object.getSenha());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @Override
    public ResponseEntity<ClienteDTO> put(Long id, ClienteDTO object) {
        return null;
    }

    @PatchMapping("/{clienteId}/status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseException.class))),
    })
    public ResponseEntity<ClienteDTO> patch(@PathVariable Long clienteId) {
        var cliente = this.clienteService.update(clienteId);
        var clienteResponse = modelMapper.map(cliente, ClienteDTO.class);
        return ResponseEntity.ok().body(clienteResponse);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
