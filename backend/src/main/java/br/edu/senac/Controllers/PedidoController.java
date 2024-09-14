package br.edu.senac.Controllers;

import br.edu.senac.DTO.CategoriaDTO;
import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Entity.PedidoEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.CategoriaService;
import br.edu.senac.Services.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Pedido")
@RequestMapping("/pedido")
@RestController
public class PedidoController implements IControllerPattern<PedidoEntity> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PagamentoService pagamentoService;


    @Override
    public ResponseEntity<PedidoEntity> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> findById(int id) {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> Insert(PedidoEntity object) {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> Update(int id, PedidoEntity object) {
        return null;
    }
}

