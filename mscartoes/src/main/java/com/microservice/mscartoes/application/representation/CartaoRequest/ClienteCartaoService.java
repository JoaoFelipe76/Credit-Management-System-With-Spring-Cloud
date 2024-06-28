package com.microservice.mscartoes.application.representation.CartaoRequest;

import com.microservice.mscartoes.domain.ClienteCartao;
import com.microservice.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    public List<ClienteCartao> findByCpf(String cpf) {

        return clienteCartaoRepository.findByCpf(cpf);

    }




}
