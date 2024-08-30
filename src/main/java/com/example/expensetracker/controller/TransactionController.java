package com.example.expensetracker.controller;

import com.example.expensetracker.dto.TransactionDto;
import com.example.expensetracker.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transactions", description = "API for managing transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction and stores it in the database")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto createdTransaction = transactionService.saveTransaction(transactionDto);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping
    @Operation(summary = "Get all transactions", description = "Retrieves all transactions from the database")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/exceeded")
    @Operation(summary = "Get transactions exceeding limit", description = "Retrieves all transactions that exceeded the spending limit")
    public ResponseEntity<List<TransactionDto>> getTransactionsExceedingLimit() {
        List<TransactionDto> transactions = transactionService.getTransactionsExceedingLimit();
        return ResponseEntity.ok(transactions);
    }
}
