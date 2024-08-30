package com.example.expensetracker.repository;

import com.example.expensetracker.entity.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpendingLimitRepository extends JpaRepository<SpendingLimit, Long> {

    Optional<SpendingLimit> findTopByOrderByLimitDatetimeDesc();
}
