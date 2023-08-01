package com.gabriel.helpdesk.service.interfaces;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.dto.ChamadoDTO;

import java.util.List;

public interface ChamadoService {

    Chamado buscaPorId(Integer id);
    List<Chamado> buscaChamados();

    Chamado salvaChamado(ChamadoDTO requestDTO);

    Chamado atualizaChamado(ChamadoDTO requestDTO);
}
