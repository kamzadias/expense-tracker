package com.example.expensetracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String accountFrom;

    @Column(nullable = false, length = 10)
    private String accountTo;

    @Column(nullable = false, length = 3)
    private String currencyShortname;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sum;

    @Column(nullable = false, length = 50)
    private String expenseCategory;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private Boolean limitExceeded;
}
