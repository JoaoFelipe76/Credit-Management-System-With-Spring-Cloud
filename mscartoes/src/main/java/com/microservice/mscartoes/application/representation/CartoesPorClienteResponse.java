package com.microservice.mscartoes.application.representation;

import com.microservice.mscartoes.domain.ClienteCartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponse {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public static CartoesPorClienteResponse from(ClienteCartao model) {


        return new CartoesPorClienteResponse(

                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()

        );


    }

}
