package com.example.expensetracker.controller;

import com.example.expensetracker.dto.CurrencyRateDto;
import com.example.expensetracker.service.CurrencyRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency-rates")
@Tag(name = "Currency Rates", description = "API for managing currency rates")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping
    @Operation(summary = "Get all currency rates", description = "Retrieves all currency rates from the database")
    public ResponseEntity<List<CurrencyRateDto>> getAllCurrencyRates() {
        List<CurrencyRateDto> rates = currencyRateService.getAllCurrencyRates();
        return ResponseEntity.ok(rates);
    }
}
