package com.microservice.mscartoes.application;

import com.microservice.mscartoes.application.representation.CartaoRequest.CartaoSaveRequest;
import com.microservice.mscartoes.application.representation.CartaoRequest.ClienteCartaoService;
import com.microservice.mscartoes.application.representation.CartoesPorClienteResponse;
import com.microservice.mscartoes.domain.Cartao;
import com.microservice.mscartoes.domain.ClienteCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {

        return "ok";

    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoSaveRequest request) {

        Cartao cartao = request.toMovel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda")Long renda) {

        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);

    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getClienteCartaoAteh(@RequestParam("cpf")String cpf) {

        List<ClienteCartao>lista =  clienteCartaoService.findByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista.stream().map(CartoesPorClienteResponse::from).toList();
        return ResponseEntity.ok(resultList);

    }






}
