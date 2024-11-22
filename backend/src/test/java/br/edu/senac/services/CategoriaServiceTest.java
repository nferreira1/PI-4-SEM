package br.edu.senac.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.edu.senac.entity.CategoriaEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.repositories.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoriaServiceTest {

  @Mock private CategoriaRepository categoriaRepository;

  @InjectMocks private CategoriaService categoriaService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Long categoriaId = 1L;
    var categoriaMock = new CategoriaEntity();
    categoriaMock.setId(categoriaId);
    categoriaMock.setNome("Categoria Teste");

    when(this.categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaMock));

    var resultado = this.categoriaService.findById(categoriaId);

    assertNotNull(resultado);
    assertEquals(categoriaId, resultado.getId());
    assertEquals("Categoria Teste", resultado.getNome());

    verify(this.categoriaRepository, times(1)).findById(categoriaId);
  }

  @Test
  void testFindByIdNotFound() {
    Long categoriaId = 0L;
    when(this.categoriaRepository.findById(categoriaId)).thenReturn(Optional.empty());

    var exception =
        assertThrows(
            ErrorResponseException.class, () -> this.categoriaService.findById(categoriaId));
    assertEquals("Categoria não encontrado.", exception.getMessage());

    verify(this.categoriaRepository, times(1)).findById(categoriaId);
  }

  @Test
  void testFindAll() {
    var categoria1 = new CategoriaEntity();
    categoria1.setId(1L);
    categoria1.setNome("Categoria 1");

    var categoria2 = new CategoriaEntity();
    categoria2.setId(2L);
    categoria2.setNome("Categoria 2");

    List<CategoriaEntity> categorias = List.of(categoria1, categoria2);
    when(this.categoriaRepository.findAll()).thenReturn(categorias);

    var resultado = this.categoriaService.findAll();

    assertNotNull(resultado);
    assertEquals(2, resultado.size());

    verify(this.categoriaRepository, times(1)).findAll();
  }
}
