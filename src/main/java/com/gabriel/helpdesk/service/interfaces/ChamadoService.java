package com.gabriel.helpdesk.service.interfaces;

import com.gabriel.helpdesk.domain.Chamado;

import java.util.List;

public interface ChamadoService {

    Chamado buscaPorId(Integer id);
    List<Chamado> buscaChamados();

}
