package com.microservice.msavaliadorcredito.ex;

import lombok.Getter;

@Getter
public class ErroComunicacaoMicroservicesExeption extends Exception {

    private Integer status;

    public ErroComunicacaoMicroservicesExeption(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
