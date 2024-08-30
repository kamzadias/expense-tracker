package com.example.expensetracker.mapper;

import com.example.expensetracker.dto.TransactionDto;
import com.example.expensetracker.entity.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return modelMapper.map(transaction, TransactionDto.class);
    }

    public Transaction toEntity(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }
}
