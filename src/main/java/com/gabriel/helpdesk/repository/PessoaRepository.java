package com.gabriel.helpdesk.repository;

import com.gabriel.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);

}
