package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.request.TecnicoRequestDTO;
import com.gabriel.helpdesk.exception.DataIntegrityViolationException;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.PessoaRepository;
import com.gabriel.helpdesk.repository.TecnicoRepository;
import com.gabriel.helpdesk.service.interfaces.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Tecnico buscaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Tecnico não encontrado com o ID informado."));
    }

    @Override
    public List<Tecnico> buscaTecnicos() {
        return repository.findAll();
    }

    @Override
    public Tecnico salvaTecnico(TecnicoRequestDTO tecnicoRequestDTO) {
        tecnicoRequestDTO.setId(null);
        validaCpfAndEmail(tecnicoRequestDTO);
        return repository.saveAndFlush(new Tecnico(tecnicoRequestDTO));
    }

    private void validaCpfAndEmail(TecnicoRequestDTO tecnicoRequestDTO) {
        if (pessoaRepository.existsByCpf(tecnicoRequestDTO.getCpf())) {
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }
        if (pessoaRepository.existsByEmail(tecnicoRequestDTO.getEmail())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }
    }
}
