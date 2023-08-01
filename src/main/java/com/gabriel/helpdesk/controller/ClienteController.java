package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.dto.ClienteDTO;
import com.gabriel.helpdesk.dto.request.ClienteRequestDTO;
import com.gabriel.helpdesk.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> buscaCliente(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(new ClienteDTO(clienteService.buscaPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscaClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(
                clienteService.buscaClientes().stream().map(ClienteDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvaCliente(@RequestBody @Valid ClienteRequestDTO requestDTO){
        Cliente Cliente = clienteService.salvaCliente(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(Cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizaCliente(@RequestBody @Valid ClienteRequestDTO requestDTO){
        return ResponseEntity.ok().body(new ClienteDTO(clienteService.atualizaCliente(requestDTO)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable(value = "id")Integer id){
        clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
