package com.example.expensetracker.controller;

import com.example.expensetracker.dto.SpendingLimitDto;
import com.example.expensetracker.service.SpendingLimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spending-limits")
@Tag(name = "Spending Limits", description = "API for managing spending limits")
public class SpendingLimitController {

    private final SpendingLimitService spendingLimitService;

    public SpendingLimitController(SpendingLimitService spendingLimitService) {
        this.spendingLimitService = spendingLimitService;
    }

    @PostMapping
    @Operation(summary = "Set a new spending limit", description = "Sets a new spending limit for expenses")
    public ResponseEntity<SpendingLimitDto> createSpendingLimit(@RequestBody SpendingLimitDto spendingLimitDto) {
        SpendingLimitDto createdLimit = spendingLimitService.saveSpendingLimit(spendingLimitDto);
        return ResponseEntity.ok(createdLimit);
    }

    @GetMapping
    @Operation(summary = "Get all spending limits", description = "Retrieves all spending limits from the database")
    public ResponseEntity<List<SpendingLimitDto>> getAllSpendingLimits() {
        List<SpendingLimitDto> limits = spendingLimitService.getAllSpendingLimits();
        return ResponseEntity.ok(limits);
    }
}
