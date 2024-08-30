package com.example.expensetracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 7)
    private String currencyPair;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDate date;
}
