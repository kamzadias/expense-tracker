package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.CurrencyRateDto;
import com.example.expensetracker.entity.CurrencyRate;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRateMapper {

    private final ModelMapper modelMapper;

    public CurrencyRateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CurrencyRateDto toDto(CurrencyRate currencyRate) {
        return modelMapper.map(currencyRate, CurrencyRateDto.class);
    }

    public CurrencyRate toEntity(CurrencyRateDto currencyRateDto) {
        return modelMapper.map(currencyRateDto, CurrencyRate.class);
    }
}
