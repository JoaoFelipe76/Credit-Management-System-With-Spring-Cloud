package com.microservice.msavaliadorcredito.infra.clients;

import com.microservice.msavaliadorcredito.domain.model.Cartao;
import com.microservice.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes",path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoCliente>> getClienteCartaoAteh(@RequestParam("cpf")String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda")Long renda);
}
