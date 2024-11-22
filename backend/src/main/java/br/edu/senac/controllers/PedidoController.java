package br.edu.senac.controllers;

import br.edu.senac.services.CategoriaService;
import br.edu.senac.services.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pedido")
@RequestMapping("/pedido")
@RestController
public class PedidoController {

  @Autowired private ModelMapper modelMapper;

  @Autowired private CategoriaService categoriaService;

  @Autowired private PedidoService pedidoService;
}
