package com.development.api.controller;

import com.development.api.domain.transactional.service.TransactionService;
import com.development.api.domain.transactional.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    public TransactionService transactionService;


    @GetMapping(path = "/list")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping(path = "/{transactionType}")
    public List<Transaction> getTransactions(@PathVariable String transactionType) {
        return transactionService.getTransactionsByType(transactionType);
    }

    @GetMapping(path = "/sum/{transactionType}")
    public BigDecimal getTotalAmount(@PathVariable String transactionType) {
        return transactionService.sumAmountsByType(transactionType);
    }
}
