package br.edu.senac.Controllers;

import br.edu.senac.Entity.PedidoEntity;
import br.edu.senac.Pattern.IControllerPattern;
import br.edu.senac.Services.CategoriaService;
import br.edu.senac.Services.PagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Pedido")
@RequestMapping("/pedido")
@RestController
public class PedidoController implements IControllerPattern<PedidoEntity, Long> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PagamentoService pagamentoService;

    @Override
    public ResponseEntity<List<PedidoEntity>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> post(PedidoEntity object) {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> put(Long id, PedidoEntity object) {
        return null;
    }

    @Override
    public ResponseEntity<PedidoEntity> delete(Long id) {
        return null;
    }

}

