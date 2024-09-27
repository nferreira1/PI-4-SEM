package br.edu.senac.Controllers;

import br.edu.senac.DTO.ClienteDTO;
import br.edu.senac.DTO.ProdutoDTO;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Entity.ProdutoEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.ClienteService;
import br.edu.senac.Services.LoginService;
import ch.qos.logback.core.net.server.Client;
import io.micrometer.observation.annotation.Observed;
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
    private LoginService loginService;

    @GetMapping
    @Override
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<ClienteEntity> clienteEntities = this.clienteService.findAll();
        List<ClienteDTO> clienteDTOS = clienteEntities.stream().
                map(cliente -> modelMapper.map(cliente, ClienteDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
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
          this.loginService.insert(cliente, object.getSenha());
        return ResponseEntity.created(uri).body(modelMapper.map(cliente, ClienteDTO.class));
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<ClienteDTO> put(@PathVariable Long id, @Valid @RequestBody ClienteDTO produtoDTO) {

        ClienteEntity cliente = clienteService.findById(id);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        modelMapper.map(produtoDTO, cliente);
        ClienteEntity clienteAtualizado = clienteService.insert(cliente);
        ClienteDTO produtoResponseDTO = modelMapper.map(clienteAtualizado, ClienteDTO.class);

        return ResponseEntity.ok().body(produtoResponseDTO);
    }
}
