package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.ChamadoRepository;
import com.gabriel.helpdesk.service.interfaces.ChamadoService;
import com.gabriel.helpdesk.service.interfaces.ClienteService;
import com.gabriel.helpdesk.service.interfaces.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;


    @Override
    public Chamado buscaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Chamado não encontrado com o ID informado."));
    }

    @Override
    public List<Chamado> buscaChamados() {
        return repository.findAll();
    }

    @Override
    public Chamado salvaOuAtualizaChamado(ChamadoDTO requestDTO) {
        return repository.saveAndFlush(novoChamado(requestDTO));
    }

    private Chamado novoChamado(ChamadoDTO requestDTO) {
        Tecnico tecnico = tecnicoService.buscaPorId(requestDTO.getTecnico());
        Cliente cliente = clienteService.buscaPorId(requestDTO.getCliente());

        Chamado chamado = new Chamado();
        if (Objects.nonNull(requestDTO.getId())) {
            chamado.setId(requestDTO.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(requestDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(requestDTO.getStatus()));
        chamado.setTitulo(requestDTO.getTitulo());
        chamado.setObservacoes(requestDTO.getObservacoes());
        return chamado;
    }

}
