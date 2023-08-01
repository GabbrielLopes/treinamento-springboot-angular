package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.service.interfaces.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<ChamadoDTO> salvaChamado(@RequestBody @Valid ChamadoDTO requestDTO){
        Chamado chamado = chamadoService.salvaChamado(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(requestDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<ChamadoDTO> atualizaChamado(@RequestBody @Valid ChamadoDTO requestDTO){
        return ResponseEntity.ok().body(new ChamadoDTO(chamadoService.atualizaChamado(requestDTO)));
    }

}

