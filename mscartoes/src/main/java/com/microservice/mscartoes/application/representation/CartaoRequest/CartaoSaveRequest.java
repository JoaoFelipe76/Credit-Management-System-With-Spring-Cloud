package com.microservice.mscartoes.application.representation.CartaoRequest;

import com.microservice.mscartoes.domain.BandeiraCartao;
import com.microservice.mscartoes.domain.Cartao;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {


    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;

    private BigDecimal renda;

    private BigDecimal limiteBasico;

    public Cartao toMovel() {

        return new Cartao(nome, bandeira, renda, limiteBasico);

    }


}
