package com.microservice.msavaliadorcredito.application;

import com.microservice.msavaliadorcredito.domain.model.*;
import com.microservice.msavaliadorcredito.ex.DadosClienteNotFoundExeption;
import com.microservice.msavaliadorcredito.ex.ErroComunicacaoMicroservicesExeption;
import com.microservice.msavaliadorcredito.ex.ErroSolicitacaoCartaoException;
import com.microservice.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.microservice.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.microservice.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;
    private final SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundExeption, ErroComunicacaoMicroservicesExeption {

        try {

            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.clienteDados(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getClienteCartaoAteh(cpf);

            return SituacaoCliente.builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {

            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {

                throw new DadosClienteNotFoundExeption();


            }
            throw new ErroComunicacaoMicroservicesExeption(e.getMessage(), status);

        }

    }

    public RetornoAvaliacaoCliente realizarAvaliacaoCliente(String cpf, Long renda) throws DadosClienteNotFoundExeption, ErroComunicacaoMicroservicesExeption {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.clienteDados(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesRendaAteh(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado cartaoAprovado = new CartaoAprovado();
                cartaoAprovado.setCartao(cartao.getNome());
                cartaoAprovado.setBandeira(cartao.getBandeira());
                cartaoAprovado.setLimiteAprovado(limiteAprovado);

                return cartaoAprovado; 

            }).toList();

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {

            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {

                throw new DadosClienteNotFoundExeption();


            }
            throw new ErroComunicacaoMicroservicesExeption(e.getMessage(), status);

        }

    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){


        try {

            solicitacaoEmissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);

        }catch (Exception e){

            throw new ErroSolicitacaoCartaoException(e.getMessage());



        }



    }
}
