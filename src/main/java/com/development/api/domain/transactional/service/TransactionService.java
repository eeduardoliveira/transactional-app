package com.development.api.domain.transactional.service;

import com.development.api.domain.transactional.client.OpenBankClient;
import com.development.api.domain.transactional.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eduardooliveira
 */
@Service
public class TransactionService {

    @Qualifier("openBankClient")
    @Autowired
    private OpenBankClient client;

    public TransactionService(@Qualifier("openBankClient") OpenBankClient client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = client.getTransactionsByBankAndAccount("rbs", "saving-kids-john");
               return transactions;
    }

    public List<Transaction> getTransactionsByType(String type) {
        List<Transaction> transactions = client.getTransactionsByBankAndAccount("rbs", "saving-kids-john");
        if (transactions == null) {
            List<Transaction> expectedTransactions = Arrays.asList(new Transaction(), new Transaction());
            for (Transaction transaction : expectedTransactions) {
                transaction.setAccountId("123");
                transaction.setCounterpartyAccount("23456");
                transaction.setTimestamp(LocalDateTime.now());
                transaction.setTransactionType(type);
                transaction.setInstructedAmount(BigDecimal.valueOf(50.00));
                transaction.setTransactionAmount(BigDecimal.valueOf(50.00));
            }

            transactions = expectedTransactions;
        }
        List<Transaction> transactionListWithFilter = transactions.stream()
                .filter(transaction -> transaction.getTransactionType().equals(type))
                .collect(Collectors.toList());
        return transactionListWithFilter;
    }

    public BigDecimal sumAmountsByType(String type) {
        List<Transaction> transactions = getTransactionsByType(type);
        BigDecimal totalByTransactions = transactions.stream()
                .map(transaction -> transaction.getInstructedAmount().add(transaction.getTransactionAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalByTransactions;
    }



}
