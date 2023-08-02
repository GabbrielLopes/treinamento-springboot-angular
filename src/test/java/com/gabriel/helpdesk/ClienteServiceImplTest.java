package com.gabriel.helpdesk;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.dto.request.ClienteRequestDTO;
import com.gabriel.helpdesk.exception.DataIntegrityViolationException;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.ClienteRepository;
import com.gabriel.helpdesk.service.interfaces.ClienteService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteServiceImplTest {

    @Autowired
    private ClienteService service;

    @MockBean
    private ClienteRepository repository;

    private static final Integer ID = 1;
    private static final Integer ID_CLIENTE = 4;
    private static final Integer ID_CHAMADO = 2;
    private Tecnico tecnico;
    private Cliente cliente;
    private ClienteRequestDTO clienteRequestDTO;
    private Chamado chamado;

    @Before
    public void setUp() {
        chamado = new Chamado(ID_CHAMADO, Prioridade.MEDIA, Status.ANDAMENTO, "Quebra de fibra",
                "Cabo quebrado no poste", tecnico, cliente);
        tecnico = new Tecnico(ID, "nomeTecnico", "12345678909", "email@mail.com", "123");
        cliente = new Cliente(ID_CLIENTE, "Torvalds", "12345678909", "email@teste.com", "123456");
        clienteRequestDTO = new ClienteRequestDTO(cliente);
    }


    @Test
    public void testBuscaPorId() {
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));
        var response = service.buscaPorId(ID);
        assertNotNull(response);
    }

    @Test
    public void testBuscaPorIdException() {
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> service.buscaPorId(ID));
    }

    @Test
    public void testBuscaClientes() {
        when(repository.findAll()).thenReturn(Collections.singletonList(cliente));
        var response = service.buscaClientes();
        assertNotNull(response);
    }

    @Test
    public void testSalvaCliente() {
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));
        when(repository.saveAndFlush(Mockito.any())).thenReturn(cliente);
        var response = service.salvaCliente(clienteRequestDTO);
        assertNotNull(response);
        verify(repository, times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void testAtualizaCliente() {
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));
        when(repository.saveAndFlush(Mockito.any())).thenReturn(cliente);
        var response = service.atualizaCliente(clienteRequestDTO);
        assertNotNull(response);
        verify(repository, times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void testDeletarPorId() {
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));
        Mockito.doNothing().when(repository).deleteById(Mockito.any());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(Mockito.any());
    }

    @Test
    public void testDeletarPorIdException() {
        cliente.setChamados(Collections.singletonList(chamado));
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(cliente));

        assertThrows(DataIntegrityViolationException.class, () -> service.deletarPorId(ID));
    }


}
