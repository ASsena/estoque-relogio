package com.backend.estoquerelogios.repository;

import com.backend.estoquerelogios.entities.Deposito;
import com.backend.estoquerelogios.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Query("SELECT SUM(e.quantidade * p.precoUnitario) " +
            "FROM Estoque e JOIN e.produto p " +
            "WHERE e.deposito = :deposito")

    BigDecimal calcularValorTotalPorDeposito(Deposito deposito);


}
