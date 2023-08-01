package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Pessoa;
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
import java.util.Optional;

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
        validaEmailECpf(tecnicoRequestDTO);
        return repository.saveAndFlush(new Tecnico(tecnicoRequestDTO));
    }

    @Override
    public Tecnico atualizaTecnico(TecnicoRequestDTO tecnicoRequestDTO) {
        Tecnico tecnicoAntigo = buscaPorId(tecnicoRequestDTO.getId());
        validaEmailECpf(tecnicoRequestDTO);
        tecnicoAntigo = new Tecnico(tecnicoRequestDTO);
        return repository.saveAndFlush(tecnicoAntigo);
    }

    private void validaEmailECpf(TecnicoRequestDTO tecnicoRequestDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoRequestDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(tecnicoRequestDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }

        obj = pessoaRepository.findByEmail(tecnicoRequestDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(tecnicoRequestDTO.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }
    }
}
