package br.edu.senac.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.repositories.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProdutoServiceTest {

  @Mock private ProdutoRepository produtoRepository;

  @InjectMocks private ProdutoService produtoService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Long produtoId = 1L;
    var produtoMock = new ProdutoEntity();
    produtoMock.setId(produtoId);
    produtoMock.setNome("Produto Teste");

    when(this.produtoRepository.findById(produtoId)).thenReturn(Optional.of(produtoMock));

    var resultado = this.produtoService.findById(produtoId);

    assertNotNull(resultado);
    assertEquals(produtoId, resultado.getId());
    assertEquals("Produto Teste", resultado.getNome());

    verify(this.produtoRepository, times(1)).findById(produtoId);
  }

  @Test
  void testFindByIdNotFound() {
    Long produtoId = 0L;
    when(this.produtoRepository.findById(produtoId)).thenReturn(Optional.empty());

    var exception =
        assertThrows(ErrorResponseException.class, () -> this.produtoService.findById(produtoId));

    assertEquals("Produto não encontrado.", exception.getMessage());

    verify(this.produtoRepository, times(1)).findById(produtoId);
  }

  @Test
  void testFindAll() {
    var produto1 = new ProdutoEntity();
    produto1.setId(1L);
    produto1.setNome("Produto 1");

    var produto2 = new ProdutoEntity();
    produto2.setId(2L);
    produto2.setNome("Produto 2");

    List<ProdutoEntity> produtos = List.of(produto1, produto2);
    when(this.produtoRepository.findAll()).thenReturn(produtos);

    var resultado = this.produtoService.findAll();

    assertNotNull(resultado);
    assertEquals(2, resultado.size());

    verify(this.produtoRepository, times(1)).findAll();
  }
}
