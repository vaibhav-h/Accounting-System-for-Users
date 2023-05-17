package com.accounting.system.service;

import com.accounting.system.dto.PageDto;
import com.accounting.system.dto.TransactionDto;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto transactionDto);
    PageDto getAllTransactions(String txnType, Integer page);
    long[][] getByTxnTpeAndLocationId(Long locationIdLong, String txnType);
}
