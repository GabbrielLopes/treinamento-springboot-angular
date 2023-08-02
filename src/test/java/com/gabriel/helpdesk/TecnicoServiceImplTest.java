package com.gabriel.helpdesk;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.dto.request.TecnicoRequestDTO;
import com.gabriel.helpdesk.exception.DataIntegrityViolationException;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.TecnicoRepository;
import com.gabriel.helpdesk.service.interfaces.TecnicoService;
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
public class TecnicoServiceImplTest {

    @Autowired
    private TecnicoService service;

    @MockBean
    private TecnicoRepository repository;

    private static final Integer ID = 1;
    private static final Integer ID_CLIENTE = 4;
    private static final Integer ID_CHAMADO = 2;
    private Tecnico tecnico;
    private Cliente cliente;
    private TecnicoRequestDTO tecnicoRequestDTO;
    private Chamado chamado;

    @Before
    public void setUp(){
        chamado = new Chamado(ID_CHAMADO, Prioridade.MEDIA, Status.ANDAMENTO,"Quebra de fibra",
                "Cabo quebrado no poste", tecnico, cliente);
        tecnico = new Tecnico(ID,"nomeTecnico","12345678909","email@mail.com","123");
        cliente = new Cliente(ID_CLIENTE,"Torvalds","12345678909","email@teste.com","123456");
        tecnicoRequestDTO = new TecnicoRequestDTO(tecnico);
    }


    @Test
    public void testBuscaPorId(){
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(tecnico));
        var response = service.buscaPorId(ID);
        assertNotNull(response);
    }

    @Test
    public void testBuscaPorIdException(){
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> service.buscaPorId(ID));
    }

    @Test
    public void testBuscaTecnicos(){
        when(repository.findAll()).thenReturn(Collections.singletonList(tecnico));
        var response = service.buscaTecnicos();
        assertNotNull(response);
    }

    @Test
    public void testSalvaTecnico(){
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(tecnico));
        when(repository.saveAndFlush(Mockito.any())).thenReturn(tecnico);
        var response = service.salvaTecnico(tecnicoRequestDTO);
        assertNotNull(response);
        verify(repository, times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void testAtualizaTecnico(){
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(tecnico));
        when(repository.saveAndFlush(Mockito.any())).thenReturn(tecnico);
        var response = service.atualizaTecnico(tecnicoRequestDTO);
        assertNotNull(response);
        verify(repository, times(1)).saveAndFlush(Mockito.any());
    }

    @Test
    public void testDeletarPorId(){
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(tecnico));
        Mockito.doNothing().when(repository).deleteById(Mockito.any());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(Mockito.any());
    }

    @Test
    public void testDeletarPorIdException(){
        tecnico.setChamados(Collections.singletonList(chamado));
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(tecnico));

        assertThrows(DataIntegrityViolationException.class, () -> service.deletarPorId(ID));
    }


}
