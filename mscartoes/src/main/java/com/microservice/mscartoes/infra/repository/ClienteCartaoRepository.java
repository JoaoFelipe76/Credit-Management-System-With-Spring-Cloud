package com.microservice.mscartoes.infra.repository;

import com.microservice.mscartoes.domain.ClienteCartao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteCartaoRepository extends CrudRepository<ClienteCartao, Long> {

    List<ClienteCartao> findByCpf(String cpf);

}
