package com.gabriel.helpdesk.service.interfaces;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;

public interface TecnicoService {

    Tecnico buscaPorId(Integer id);

}
