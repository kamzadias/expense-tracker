package com.example.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "Data Transfer Object for Currency Rate")
public class CurrencyRateDto {

    @Schema(description = "Currency pair", example = "KZT/USD")
    private String currencyPair;

    @Schema(description = "Exchange rate for the currency pair", example = "0.0023")
    private BigDecimal rate;

    @Schema(description = "Date of the exchange rate", example = "2022-01-30")
    private LocalDate date;
}
