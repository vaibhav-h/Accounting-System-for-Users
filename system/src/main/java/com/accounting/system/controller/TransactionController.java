package com.accounting.system.controller;

import com.accounting.system.dto.PageDto;
import com.accounting.system.dto.TransactionDto;
import com.accounting.system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/transactions")
public class  TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> creteTransaction(@Valid @RequestBody TransactionDto transactionDto){
        TransactionDto transactionDtoCreated = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transactionDtoCreated, HttpStatus.CREATED);
    }

    @GetMapping("/transaction")
    public ResponseEntity<PageDto> getAllTransactions(@RequestParam (value = "transactionType") String transactionType,
                                                      @RequestParam (value = "pageNumber" , defaultValue = "1" ) Integer pageNumber){
        PageDto pageDto = transactionService.getAllTransactions(transactionType, pageNumber);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @GetMapping("/transaction/{locationId}/{transactionType}")
    public ResponseEntity<long[][]> getAllTransactions(@PathVariable  Long locationId,
                                                         @PathVariable  String transactionType
                                                      ){
        long[][] arr = transactionService.getByTxnTpeAndLocationId(locationId, transactionType);
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

}
