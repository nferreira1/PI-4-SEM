package br.edu.senac.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.edu.senac.entity.GeneroEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.repositories.GeneroRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GeneroServiceTest {

  @Mock private GeneroRepository generoRepository;

  @InjectMocks private GeneroService generoService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Long generoId = 1L;
    var generoMock = new GeneroEntity();
    generoMock.setId(generoId);
    generoMock.setNome("Genero Teste");

    when(this.generoRepository.findById(generoId)).thenReturn(Optional.of(generoMock));

    var resultado = this.generoService.findById(generoId);

    assertNotNull(resultado);
    assertEquals(generoId, resultado.getId());
    assertEquals("Genero Teste", resultado.getNome());

    verify(this.generoRepository, times(1)).findById(generoId);
  }

  @Test
  void testFindByIdNotFound() {
    Long generoId = 0L;
    when(this.generoRepository.findById(generoId)).thenReturn(Optional.empty());

    var exception =
        assertThrows(ErrorResponseException.class, () -> this.generoService.findById(generoId));
    assertEquals("Genero não encontrado.", exception.getMessage());

    verify(this.generoRepository, times(1)).findById(generoId);
  }

  @Test
  void testFindAll() {
    var genero1 = new GeneroEntity();
    genero1.setId(1L);
    genero1.setNome("Genero 1");

    var genero2 = new GeneroEntity();
    genero2.setId(2L);
    genero2.setNome("Genero 2");

    List<GeneroEntity> generos = List.of(genero1, genero2);
    when(this.generoRepository.findAll()).thenReturn(generos);

    var resultado = this.generoService.findAll();

    assertNotNull(resultado);
    assertEquals(2, resultado.size());

    verify(this.generoRepository, times(1)).findAll();
  }
}
