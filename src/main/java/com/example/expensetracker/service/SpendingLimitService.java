package com.example.expensetracker.service;

import com.example.expensetracker.dto.SpendingLimitDto;
import com.example.expensetracker.entity.SpendingLimit;
import com.example.expensetracker.mapper.SpendingLimitMapper;
import com.example.expensetracker.repository.SpendingLimitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpendingLimitService {

    private final SpendingLimitRepository spendingLimitRepository;
    private final SpendingLimitMapper spendingLimitMapper;

    public SpendingLimitService(SpendingLimitRepository spendingLimitRepository,
                                SpendingLimitMapper spendingLimitMapper) {
        this.spendingLimitRepository = spendingLimitRepository;
        this.spendingLimitMapper = spendingLimitMapper;
    }

    public SpendingLimitDto saveSpendingLimit(SpendingLimitDto spendingLimitDto) {
        SpendingLimit spendingLimit = spendingLimitMapper.toEntity(spendingLimitDto);
        return spendingLimitMapper.toDto(spendingLimitRepository.save(spendingLimit));
    }

    public List<SpendingLimitDto> getAllSpendingLimits() {
        return spendingLimitRepository.findAll().stream()
                .map(spendingLimitMapper::toDto)
                .collect(Collectors.toList());
    }
}
