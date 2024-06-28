package com.microservice.msclientes.application;

import com.microservice.msclientes.domain.Cliente;
import com.microservice.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    public Optional<Cliente> findByCpf(String cpf) {

        return clienteRepository.findByCpf(cpf);

    }


}
