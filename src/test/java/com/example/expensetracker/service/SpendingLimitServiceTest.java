package com.example.expensetracker.service;

import com.example.expensetracker.dto.SpendingLimitDto;
import com.example.expensetracker.entity.SpendingLimit;
import com.example.expensetracker.mapper.SpendingLimitMapper;
import com.example.expensetracker.repository.SpendingLimitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpendingLimitServiceTest {

    @Mock
    private SpendingLimitRepository spendingLimitRepository;

    @Mock
    private SpendingLimitMapper spendingLimitMapper;

    @InjectMocks
    private SpendingLimitService spendingLimitService;

    private SpendingLimitDto spendingLimitDto;
    private SpendingLimit spendingLimit;

    @BeforeEach
    void setUp() {
        spendingLimitDto = new SpendingLimitDto();
        spendingLimitDto.setLimitSum(new BigDecimal("1000"));
        spendingLimitDto.setLimitDatetime(LocalDateTime.now());
        spendingLimitDto.setCurrencyShortname("USD");

        spendingLimit = new SpendingLimit();
        spendingLimit.setId(1L);
        spendingLimit.setLimitSum(new BigDecimal("1000"));
        spendingLimit.setLimitDatetime(LocalDateTime.now());
        spendingLimit.setCurrencyShortname("USD");
    }

    @Test
    void testSaveSpendingLimit_ShouldReturnSavedSpendingLimitDto() {
        // Arrange
        when(spendingLimitMapper.toEntity(any(SpendingLimitDto.class))).thenReturn(spendingLimit);
        when(spendingLimitRepository.save(any(SpendingLimit.class))).thenReturn(spendingLimit);
        when(spendingLimitMapper.toDto(any(SpendingLimit.class))).thenReturn(spendingLimitDto);

        // Act
        SpendingLimitDto savedSpendingLimitDto = spendingLimitService.saveSpendingLimit(spendingLimitDto);

        // Assert
        assertNotNull(savedSpendingLimitDto);
        assertEquals(spendingLimitDto.getLimitSum(), savedSpendingLimitDto.getLimitSum());
        assertEquals(spendingLimitDto.getCurrencyShortname(), savedSpendingLimitDto.getCurrencyShortname());
        verify(spendingLimitMapper, times(1)).toEntity(any(SpendingLimitDto.class));
        verify(spendingLimitRepository, times(1)).save(any(SpendingLimit.class));
        verify(spendingLimitMapper, times(1)).toDto(any(SpendingLimit.class));
    }

    @Test
    void testGetAllSpendingLimits_ShouldReturnSpendingLimitDtoList() {
        // Arrange
        List<SpendingLimit> spendingLimitList = Arrays.asList(spendingLimit);
        List<SpendingLimitDto> spendingLimitDtoList = Arrays.asList(spendingLimitDto);

        when(spendingLimitRepository.findAll()).thenReturn(spendingLimitList);
        when(spendingLimitMapper.toDto(any(SpendingLimit.class))).thenReturn(spendingLimitDto);

        // Act
        List<SpendingLimitDto> result = spendingLimitService.getAllSpendingLimits();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(spendingLimitDto.getLimitSum(), result.get(0).getLimitSum());
        verify(spendingLimitRepository, times(1)).findAll();
        verify(spendingLimitMapper, times(1)).toDto(any(SpendingLimit.class));
    }
}
