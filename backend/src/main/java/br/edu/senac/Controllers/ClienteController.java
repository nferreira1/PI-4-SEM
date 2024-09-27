package br.edu.senac.Controllers;

import br.edu.senac.DTO.ClienteDTO;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.ClienteService;
import ch.qos.logback.core.net.server.Client;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Cliente")
@RequestMapping("/cliente")
@RestController
public class ClienteController implements IControllerPattern<ClienteDTO, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService clienteService;

//    @Autowired
//    private LoginService loginService;


    @Override
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ClienteDTO> getById(Long id) {
        return ResponseEntity.ok().body(modelMapper.map(this.clienteService.findById(id), ClienteDTO.class));
    }

    @PostMapping
    @Override
    public ResponseEntity<ClienteDTO> post(ClienteDTO object) {
        var cliente = modelMapper.map(object, ClienteEntity.class);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        this.clienteService.insert(cliente);
//        this.loginService.insert(cliente, clienteDTO.getSenha());
        return ResponseEntity.created(uri).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @Override
    public ResponseEntity<ClienteDTO> put(Long id, ClienteDTO object) {
        return null;
    }
}
