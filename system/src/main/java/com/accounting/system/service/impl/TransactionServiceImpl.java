package com.accounting.system.service.impl;

import com.accounting.system.dto.PageDto;
import com.accounting.system.dto.TransactionDto;
import com.accounting.system.entity.Transaction;
import com.accounting.system.exception.IpAlreadyExistsException;
import com.accounting.system.exception.ResourceNotFoundException;
import com.accounting.system.exception.UserIdAlreadyExists;
import com.accounting.system.mapper.TransactionMapper;
import com.accounting.system.repository.LocationRepository;
import com.accounting.system.repository.TransactionRepository;
import com.accounting.system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {

        TransactionDto transactionDto1 = new TransactionDto();
        String existingIp = transactionRepository.findIpById(transactionDto.getUserId());

        String existingUserName = transactionRepository.findUserNameById(transactionDto.getUserId());

        List<String> allIps = transactionRepository.findAllIds();
        if (existingUserName == null || existingUserName.equalsIgnoreCase(transactionDto.getUserName())) {
            if (allIps.contains(transactionDto.getIp())) {
                Long userId = transactionRepository.findUserByIp(transactionDto.getIp());
                if (transactionDto.getUserId() == userId) {
                    transactionDto.setAmount("$" + transactionDto.getAmount());
                    Transaction transaction = TransactionMapper.mapper.mapToTransaction(transactionDto);

                    Transaction savedTransaction = transactionRepository.save(transaction);
                    TransactionDto transactionDtoSaved = TransactionMapper.mapper.mapToTransactionDto(savedTransaction);
                    transactionDto1 = transactionDtoSaved;
                }
                else{
                    throw new IpAlreadyExistsException("IP Already Exists for another user");
                }
            } else {
                transactionDto.setAmount("$" + transactionDto.getAmount());
                Transaction transaction = TransactionMapper.mapper.mapToTransaction(transactionDto);

                Transaction savedTransaction = transactionRepository.save(transaction);
                TransactionDto transactionDtoSaved = TransactionMapper.mapper.mapToTransactionDto(savedTransaction);
                transactionDto1 = transactionDtoSaved;
            }

        }
        else{
            throw new UserIdAlreadyExists("UserId Already Exists for another user");
        }
        return transactionDto1;
    }

    @Override
    public PageDto getAllTransactions(String txnType, Integer pageNumber) {
        int pageSize = 10;
        Pageable paging = PageRequest.of(pageNumber - 1, pageSize);
        Page<Transaction> pagedResult = transactionRepository.findAllTransactionsWithPagination(txnType, paging);
        PageDto pageDto = new PageDto();

        if (pagedResult.hasContent()) {
            List<Transaction> transactionList = pagedResult.getContent();
            pageDto.setPage(pageNumber);
            pageDto.setTotal_pages(pagedResult.getTotalPages());
            pageDto.setTotal(pagedResult.getTotalElements());
            pageDto.setPer_page(pagedResult.getSize());

            List<TransactionDto> allTransactions = transactionList.stream().map(TransactionMapper.mapper::mapToTransactionDto).collect(Collectors.toList());

            pageDto.setData(allTransactions);
        } else {

            throw new ResourceNotFoundException("No Records Found");
        }

        return pageDto;
    }

    @Override
    public long[][] getByTxnTpeAndLocationId(Long locationId, String txnType) {
        List<Transaction> transactions = transactionRepository.findAllTransactionsBTxnTypeAndLocation(locationId, txnType);

        Map<Long, Double> map = new HashMap<>();

        for (int i = 0; i < transactions.size(); i++) {
            Long userId = transactions.get(i).getUserId();
            String amt = transactions.get(i).getAmount();

            if (map.containsKey(userId)) {
                double value = Double.parseDouble(amt.substring(1));
                map.put(userId, map.get(userId) + value);
            } else {
                double value = Double.parseDouble(amt.substring(1));
                map.put(userId, value);
            }

        }
        if(map.size() != 0){

            int rows = map.size();
            long arr1[][] = new long[rows][2];
            int low = 0;
            for (Long i : map.keySet()) {
                arr1[low][0] = (long) i;
                arr1[low][1] = (long) Math.round(map.get(i));
                low++;
            }
            return arr1;

            }
            else{
                return new long[][]{{-1, -1}};
            }
        }
}


