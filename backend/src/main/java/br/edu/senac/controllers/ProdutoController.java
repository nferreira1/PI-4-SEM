package br.edu.senac.controllers;

import br.edu.senac.DTO.ProdutoDTO;
import br.edu.senac.models.Produto;
import br.edu.senac.services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Produto")
@RequestMapping("/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ModelMapper modelMapper; //pra mapear uma dto através de uma classe

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> produtos = this.produtoService.findAll();
        List<ProdutoDTO> produtoDTOS = produtos.stream().
                map(produto -> modelMapper.map(produto, ProdutoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(produtoDTOS);
    }
}
