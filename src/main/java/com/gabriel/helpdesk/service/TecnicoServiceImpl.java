package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.TecnicoRepository;
import com.gabriel.helpdesk.service.interfaces.TecnicoService;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Override
    public Tecnico buscaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Tecnico não encontrado com o ID informado."));
    }

    @Override
    public List<Tecnico> buscaTecnicos() {
        return repository.findAll();
    }
}
