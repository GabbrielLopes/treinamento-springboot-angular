package com.gabriel.helpdesk.service;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.domain.enums.Perfil;
import com.gabriel.helpdesk.domain.enums.Prioridade;
import com.gabriel.helpdesk.domain.enums.Status;
import com.gabriel.helpdesk.repository.ChamadoRepository;
import com.gabriel.helpdesk.repository.ClienteRepository;
import com.gabriel.helpdesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "12345678911", "email@gmail.com",
                "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "09876543211", "torvalds@gmail.com",
                "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01",
                "Primeiro chamado",
                tec1, cli1);

        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "12345678912", "stallman@gnu.org",
                "123");
        tec2.addPerfil(Perfil.TECNICO);

        Tecnico tec3 = new Tecnico(null, "Bruce Perens", "12345678913", "perens@oreilly.com",
                "123");
        tec3.addPerfil(Perfil.TECNICO);

        Tecnico tec4 = new Tecnico(null, "Antonio Lira", "12345678914", "antoniolira@gmail.com",
                "123");
        tec4.addPerfil(Perfil.ADMIN);

        Tecnico tec5 = new Tecnico(null, "Larry Souza", "12345678915",
                "larrysouza@perl.org", "123");
        tec5.addPerfil(Perfil.TECNICO);

        Tecnico tec6 = new Tecnico(null, "Brian Behlendorf", "12345678916",
                "behlendorf@apache.org", "123");
        tec6.addPerfil(Perfil.TECNICO);

        Cliente cli2 = new Cliente(null, "Stallman", "09876543212",
                "stell@gnu.org", "123");

        Cliente cli3 = new Cliente(null, "Bruce Stoll", "09876543213",
                "perenstoll@oreilly.com", "123");

        Cliente cli4 = new Cliente(null, "Larry Wall", "09876543214", "wall@perl.org", "123");

        Cliente cli5 = new Cliente(null, "Brian Connor", "09876543215",
                "brianconnor@apache.org", "123");

        Cliente cli6 = new Cliente(null, "Eric S. Raymond", "09876543216",
                "esr@thyrsus.com", "123");

        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 02",
                "Segundo chamado",
                tec2, cli2);

        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 03",
                "Terceiro chamado",
                tec3, cli3);

        Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 04",
                "Quarto chamado",
                tec4, cli4);

        Chamado c5 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 05", "Quinto chamado",
                tec5, cli5);

        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 06", "Sexto chamado",
                tec6, cli6);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

    }

}
