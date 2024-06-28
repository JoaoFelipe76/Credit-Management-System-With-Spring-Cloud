package com.microservice.msclientes.application.representation;

import com.microservice.msclientes.domain.Cliente;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteSaveRequest {

    @Column
    private String cpf;

    @Column
    private String nome;

    @Column
    private int idade;

    public Cliente toModel() {


        return new Cliente(cpf, nome, idade);


    }

}
