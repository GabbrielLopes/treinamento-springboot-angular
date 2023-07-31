package com.gabriel.helpdesk.service.interfaces;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.dto.request.TecnicoRequestDTO;

import java.util.List;

public interface TecnicoService {

    Tecnico buscaPorId(Integer id);

    List<Tecnico> buscaTecnicos();

    Tecnico salvaTecnico(TecnicoRequestDTO tecnicoRequestDTO);
}
