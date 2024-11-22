package br.edu.senac.controllers;

import br.edu.senac.dto.PagamentoDTO;
import br.edu.senac.entity.*;
import br.edu.senac.enums.StatusPagamento;
import br.edu.senac.services.CarrinhoService;
import br.edu.senac.services.PagamentoService;
import br.edu.senac.services.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "Pagamento")
@RequestMapping("/pagamento")
@RestController
public class PagamentoController {

  @Autowired private ModelMapper modelMapper;

  @Autowired private PagamentoService pagamentoService;

  @Autowired private CarrinhoService carrinhoService;

  @Autowired private PedidoService pedidoService;

  @PostMapping("/{carrinhoId}")
  public ResponseEntity<PagamentoDTO> Post(
      @PathVariable Long carrinhoId, @RequestBody @Valid PagamentoDTO pagamentoDTO) {

    var pagamento = modelMapper.map(pagamentoDTO, PagamentoEntity.class);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pagamento.getId())
            .toUri();

    pagamento = this.pagamentoService.insert(pagamento);

    CarrinhoEntity carrinho = carrinhoService.findById(carrinhoId);
    List<ProdutoEntity> listProdutos = carrinhoService.carregarProdutosDoCarrinho(carrinhoId);
    PedidoEntity pedido =
        pedidoService.insert(
            new PedidoEntity(null, LocalDateTime.now(), carrinho.getClienteEntity(), pagamento));

    if (pagamento.getStatusPagamento().equals(StatusPagamento.PENDENTE)) {
      pedidoService.gerarPedidoItem(pedido, carrinho);
    }

    pagamento.setStatusPagamento(StatusPagamento.APROVADO);

    pagamento = pagamentoService.update(pagamento.getId(), pagamento);

    return ResponseEntity.created(uri).body(modelMapper.map(pagamento, PagamentoDTO.class));
  }
}
