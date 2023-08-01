package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.ChamadoRepository;
import com.gabriel.helpdesk.service.interfaces.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    @Autowired
    private ChamadoRepository repository;


    @Override
    public Chamado buscaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Chamado não encontrado com o ID informado."));
    }

    @Override
    public List<Chamado> buscaChamados() {
        return repository.findAll();
    }
}
