package com.microservice.msavaliadorcredito.ex;

public class ErroSolicitacaoCartaoException extends  RuntimeException{

    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}
