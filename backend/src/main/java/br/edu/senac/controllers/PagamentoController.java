package br.edu.senac.controllers;

import br.edu.senac.dto.PagamentoDTO;
import br.edu.senac.entity.PagamentoEntity;
import br.edu.senac.services.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
