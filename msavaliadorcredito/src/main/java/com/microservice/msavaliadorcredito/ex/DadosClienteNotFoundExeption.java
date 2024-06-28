package com.microservice.msavaliadorcredito.ex;

public class DadosClienteNotFoundExeption extends Exception{

    public DadosClienteNotFoundExeption() {
        super("Dados do cliente não encontrados para o CPF informado.");
    }
}
