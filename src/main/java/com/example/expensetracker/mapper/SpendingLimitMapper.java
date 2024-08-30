package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.SpendingLimitDto;
import com.example.expensetracker.entity.SpendingLimit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SpendingLimitMapper {

    private final ModelMapper modelMapper;

    public SpendingLimitMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SpendingLimitDto toDto(SpendingLimit spendingLimit) {
        return modelMapper.map(spendingLimit, SpendingLimitDto.class);
    }

    public SpendingLimit toEntity(SpendingLimitDto spendingLimitDto) {
        return modelMapper.map(spendingLimitDto, SpendingLimit.class);
    }
}
