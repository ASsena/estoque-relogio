package com.backend.estoquerelogios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Deposito extends JpaRepository<Deposito, Long> {
}
