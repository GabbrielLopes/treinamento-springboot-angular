package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.dto.request.TecnicoRequestDTO;
import com.gabriel.helpdesk.service.interfaces.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> buscaTecnico(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(new TecnicoDTO(tecnicoService.buscaPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> buscaTecnicos(){
        return ResponseEntity.status(HttpStatus.OK).body(
                tecnicoService.buscaTecnicos().stream().map(TecnicoDTO::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> salvaTecnico(@RequestBody @Valid TecnicoRequestDTO requestDTO){
        Tecnico tecnico = tecnicoService.salvaTecnico(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<TecnicoDTO> atualizaTecnico(@RequestBody @Valid TecnicoRequestDTO requestDTO){
        return ResponseEntity.ok().body(new TecnicoDTO(tecnicoService.atualizaTecnico(requestDTO)));
    }

}
