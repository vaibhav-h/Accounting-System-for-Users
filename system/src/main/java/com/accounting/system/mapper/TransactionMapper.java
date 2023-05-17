package com.accounting.system.mapper;

import com.accounting.system.dto.TransactionDto;
import com.accounting.system.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses= {LocationMapper.class})
public interface TransactionMapper {
    TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);

    TransactionDto mapToTransactionDto(Transaction transaction);

    Transaction mapToTransaction(TransactionDto transactionDto);

}
