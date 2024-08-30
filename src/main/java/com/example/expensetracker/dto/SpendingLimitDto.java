package com.example.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "Data Transfer Object for Spending Limit")
public class SpendingLimitDto {

    @Schema(description = "The spending limit amount", example = "1000.00")
    private BigDecimal limitSum;

    @Schema(description = "The date and time when the limit was set", example = "2022-01-10T00:00:00+06:00")
    private LocalDateTime limitDatetime;

    @Schema(description = "Currency of the spending limit", example = "USD")
    private String currencyShortname;
}
