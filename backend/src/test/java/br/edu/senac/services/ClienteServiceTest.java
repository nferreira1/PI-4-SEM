package br.edu.senac.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.repositories.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClienteServiceTest {

  @Mock private ClienteRepository clienteRepository;

  @InjectMocks private ClienteService clienteService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testFindById() {
    Long clienteId = 1L;
    var clienteMock = new ClienteEntity();
    clienteMock.setId(clienteId);
    clienteMock.setNome("Cliente Teste");

    when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteMock));

    var resultado = this.clienteService.findById(clienteId);

    assertNotNull(resultado);
    assertEquals(clienteId, resultado.getId());
    assertEquals("Cliente Teste", resultado.getNome());

    verify(this.clienteRepository, times(1)).findById(clienteId);
  }

  @Test
  void testFindByIdNotFound() {
    Long clienteId = 0L;
    when(this.clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

    var exception =
        assertThrows(ErrorResponseException.class, () -> this.clienteService.findById(clienteId));
    assertEquals("Cliente não encontrado.", exception.getMessage());

    verify(this.clienteRepository, times(1)).findById(clienteId);
  }

  @Test
  void testFindAll() {
    var cliente1 = new ClienteEntity();
    cliente1.setId(1L);
    cliente1.setNome("Cliente 1");

    var cliente2 = new ClienteEntity();
    cliente2.setId(2L);
    cliente2.setNome("Cliente 2");

    List<ClienteEntity> clientes = List.of(cliente1, cliente2);
    when(this.clienteRepository.findAll()).thenReturn(clientes);

    var resultado = this.clienteService.findAll();

    assertNotNull(resultado);
    assertEquals(2, resultado.size());

    verify(this.clienteRepository, times(1)).findAll();
  }
}
