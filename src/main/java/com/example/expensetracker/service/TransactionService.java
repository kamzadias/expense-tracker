package com.example.expensetracker.service;

import com.example.expensetracker.dto.TransactionDto;
import com.example.expensetracker.entity.SpendingLimit;
import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.mapper.TransactionMapper;
import com.example.expensetracker.repository.SpendingLimitRepository;
import com.example.expensetracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final SpendingLimitRepository spendingLimitRepository;
    private final TransactionMapper transactionMapper;
    private final CurrencyRateService currencyRateService;

    public TransactionService(TransactionRepository transactionRepository,
                              SpendingLimitRepository spendingLimitRepository,
                              TransactionMapper transactionMapper,
                              CurrencyRateService currencyRateService) {
        this.transactionRepository = transactionRepository;
        this.spendingLimitRepository = spendingLimitRepository;
        this.transactionMapper = transactionMapper;
        this.currencyRateService = currencyRateService;
    }

    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);

        // Конвертация суммы в USD
        BigDecimal exchangeRate = currencyRateService.getExchangeRate(transaction.getCurrencyShortname(), "USD");
        BigDecimal sumInUsd = transaction.getSum().multiply(exchangeRate);

        // Получение текущего лимита
        SpendingLimit spendingLimit = spendingLimitRepository.findTopByOrderByLimitDatetimeDesc()
                .orElseGet(() -> new SpendingLimit(BigDecimal.valueOf(1000), LocalDateTime.now(), "USD"));

        // Подсчет общих расходов в этом месяце
        BigDecimal totalSpent = transactionRepository.findTotalSpentInCurrentMonth(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear())
                .orElse(BigDecimal.ZERO);

        // Проверка на превышение лимита
        boolean limitExceeded = totalSpent.add(sumInUsd).compareTo(spendingLimit.getLimitSum()) > 0;
        transaction.setLimitExceeded(limitExceeded);

        // Сохранение транзакции
        transactionRepository.save(transaction);

        return transactionMapper.toDto(transaction);
    }

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TransactionDto> getTransactionsExceedingLimit() {
        return transactionRepository.findByLimitExceededTrue().stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }
}