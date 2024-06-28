package com.microservice.msavaliadorcredito.infra.clients;

import com.microservice.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes",path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<DadosCliente> clienteDados(@RequestParam String cpf);


}
