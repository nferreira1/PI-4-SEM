package br.edu.senac.controllers;

import br.edu.senac.dto.ClienteDTO;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.IControllerPattern;
import br.edu.senac.services.ClienteService;
import br.edu.senac.services.GeneroService;
import br.edu.senac.services.LoginService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private LoginService loginService;

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<ClienteEntity> clienteEntities = this.clienteService.findAll();
        List<ClienteDTO> clienteDTOS = clienteEntities.stream().
                map(cliente -> modelMapper.map(cliente, ClienteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @Override
    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long clienteId) {
        return ResponseEntity.ok().body(modelMapper.map(this.clienteService.findById(clienteId), ClienteDTO.class));
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteDTO> post(@RequestBody ClienteDTO object) {
        this.generoService.findById(object.getGeneroId());
        var cliente = modelMapper.map(object, ClienteEntity.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        cliente = this.clienteService.insert(cliente);
        this.loginService.insert(cliente, object.getSenha());
        return ResponseEntity.created(uri).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @Override
    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> put(@PathVariable Long clienteId, @Valid @RequestBody ClienteDTO produtoDTO) {

        ClienteEntity cliente = clienteService.findById(clienteId);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        modelMapper.map(produtoDTO, cliente);
        ClienteEntity clienteAtualizado = clienteService.insert(cliente);
        ClienteDTO produtoResponseDTO = modelMapper.map(clienteAtualizado, ClienteDTO.class);

        return ResponseEntity.ok().body(produtoResponseDTO);
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
