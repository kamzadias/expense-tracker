package com.example.expensetracker.repository;

import com.example.expensetracker.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    Optional<CurrencyRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date);
}
