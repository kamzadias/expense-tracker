package com.example.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "Data Transfer Object for Transaction")
public class TransactionDto {

    @Schema(description = "Account from which the transaction is made", example = "0000000123")
    private String accountFrom;

    @Schema(description = "Account to which the transaction is made", example = "9999999999")
    private String accountTo;

    @Schema(description = "Currency used in the transaction", example = "KZT")
    private String currencyShortname;

    @Schema(description = "Transaction amount", example = "10000.45")
    private BigDecimal sum;

    @Schema(description = "Category of expense", example = "service")
    private String expenseCategory;

    @Schema(description = "Date and time of the transaction", example = "2022-01-30T00:00:00+06:00")
    private LocalDateTime datetime;

    @Schema(description = "Flag indicating if the transaction exceeded the limit", example = "false")
    private Boolean limitExceeded;
}
