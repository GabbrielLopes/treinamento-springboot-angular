package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.dto.ClienteDTO;
import com.gabriel.helpdesk.service.interfaces.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> buscaChamado(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(new ChamadoDTO(chamadoService.buscaPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscaChamados(){
        return ResponseEntity.status(HttpStatus.OK).body(
                chamadoService.buscaChamados().stream().map(ChamadoDTO::new).collect(Collectors.toList()));
    }
}

