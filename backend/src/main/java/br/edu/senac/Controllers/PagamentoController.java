package br.edu.senac.Controllers;

import br.edu.senac.DTO.ClienteDTO;
import br.edu.senac.DTO.PagamentoDTO;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Entity.PagamentoEntity;
import br.edu.senac.Services.ClienteService;
import br.edu.senac.Services.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Pagamento")
@RequestMapping("/pagamento")
@RestController
public class PagamentoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoEntity> Post(@RequestBody @Valid PagamentoDTO clienteDTO) {
        return null;
    }
}
