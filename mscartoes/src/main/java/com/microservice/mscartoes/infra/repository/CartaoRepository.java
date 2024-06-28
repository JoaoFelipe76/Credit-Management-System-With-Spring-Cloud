package com.microservice.mscartoes.infra.repository;

import com.microservice.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Query("select c from Cartao c where c.renda <= ?1")
    List<Cartao> findByRendaLessThanEqual(BigDecimal renda);

}
