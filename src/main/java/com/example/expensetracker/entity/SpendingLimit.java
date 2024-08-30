package com.example.expensetracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class SpendingLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal limitSum;

    @Column(nullable = false)
    private LocalDateTime limitDatetime;

    @Column(nullable = false, length = 3)
    private String currencyShortname;

    public SpendingLimit(BigDecimal limitSum, LocalDateTime limitDatetime, String currencyShortname) {
        this.limitSum = limitSum;
        this.limitDatetime = limitDatetime;
        this.currencyShortname = currencyShortname;
    }
}
