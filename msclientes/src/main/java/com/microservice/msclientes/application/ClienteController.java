package com.microservice.msclientes.application;

import com.microservice.msclientes.application.representation.ClienteSaveRequest;
import com.microservice.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String status(){

        log.info("cliente status");
        return "ok";

    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) {

        Cliente cliente = request.toModel();
        clienteService.save(cliente);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();


    }

    @GetMapping(params = "cpf")
    public ResponseEntity clienteDados(@RequestParam String cpf) {

        var cliente = clienteService.findByCpf(cpf);

        if (cliente.isEmpty()) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(cliente);

    }


}
