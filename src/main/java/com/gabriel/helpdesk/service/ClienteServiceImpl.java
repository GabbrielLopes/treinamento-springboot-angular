package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Pessoa;
import com.gabriel.helpdesk.dto.request.ClienteRequestDTO;
import com.gabriel.helpdesk.exception.DataIntegrityViolationException;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repository.ClienteRepository;
import com.gabriel.helpdesk.repository.PessoaRepository;
import com.gabriel.helpdesk.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Cliente buscaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Cliente não encontrado com o ID informado."));
    }

    @Override
    public List<Cliente> buscaClientes() {
        return repository.findAll();
    }

    @Override
    public Cliente salvaCliente(ClienteRequestDTO cliente) {
        cliente.setId(null);
        validaEmailECpf(cliente);
        return repository.saveAndFlush(new Cliente(cliente));
    }

    @Override
    public Cliente atualizaCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente clienteAntigo = buscaPorId(clienteRequestDTO.getId());
        validaEmailECpf(clienteRequestDTO);
        clienteAntigo = new Cliente(clienteRequestDTO);
        return repository.saveAndFlush(clienteAntigo);
    }

    @Override
    public void deletarPorId(Integer id) {
        Cliente cliente = buscaPorId(id);
        if (!cliente.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaEmailECpf(ClienteRequestDTO clienteRequestDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteRequestDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(clienteRequestDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado.");
        }

        obj = pessoaRepository.findByEmail(clienteRequestDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(clienteRequestDTO.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }
    }
}
