package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.repository.TecnicoRepository;
import com.gabriel.helpdesk.service.interfaces.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Override
    public Tecnico buscaPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
