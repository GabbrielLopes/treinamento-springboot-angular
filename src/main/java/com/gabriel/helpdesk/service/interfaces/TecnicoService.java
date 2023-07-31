package com.gabriel.helpdesk.service.interfaces;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;

import java.util.List;

public interface TecnicoService {

    Tecnico buscaPorId(Integer id);

    List<Tecnico> buscaTecnicos();
}
