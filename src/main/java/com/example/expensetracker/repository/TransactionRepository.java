package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.sum) FROM Transaction t WHERE MONTH(t.datetime) = :month AND YEAR(t.datetime) = :year")
    Optional<BigDecimal> findTotalSpentInCurrentMonth(int month, int year);

    List<Transaction> findByLimitExceededTrue();
}
