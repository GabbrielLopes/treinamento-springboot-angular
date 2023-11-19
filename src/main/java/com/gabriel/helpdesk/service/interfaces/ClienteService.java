package com.gabriel.helpdesk.service.interfaces;

import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.dto.request.ClienteRequestDTO;

import java.util.List;

public interface ClienteService {

    Cliente buscaPorId(Integer id);

    List<Cliente> buscaClientes();

    Cliente salvaCliente(ClienteRequestDTO cliente);

    Cliente atualizaCliente(ClienteRequestDTO cliente);

    void deletarPorId(Integer id);
}
