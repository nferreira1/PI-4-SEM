package br.edu.senac.Controllers;

import br.edu.senac.DTO.ClienteDTO;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Cliente")
@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService clienteService;

//    @Autowired
//    private LoginService loginService;

    @PostMapping
    public ResponseEntity<ClienteDTO> Post(@RequestBody @Valid ClienteDTO clienteDTO) {
        var cliente = modelMapper.map(clienteDTO, ClienteEntity.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        this.clienteService.insert(cliente);
//        this.loginService.insert(cliente, clienteDTO.getSenha());
        return ResponseEntity.created(uri).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> GetId(@PathVariable @Valid Long id) {
        return ResponseEntity.ok().body(modelMapper.map(this.clienteService.findById(id), ClienteDTO.class));
    }


}
