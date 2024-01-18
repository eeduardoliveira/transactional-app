package com.development.api.Transaction.Service;

import com.development.api.domain.transactional.client.OpenBankClient;
import com.development.api.domain.transactional.model.Transaction;
import com.development.api.domain.transactional.service.TransactionService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionalServiceTest {

    String bankId = "rbs";
    String accountId = "saving-kids-john";

    @Test
    public List<Transaction> getTransactions_shouldReturnTransactions() {
        List<Transaction> transactionList = Arrays.asList(new Transaction(), new Transaction());

        OpenBankClient mockOpenBankClient = mock(OpenBankClient.class);

        when(mockOpenBankClient.getTransactionsByBankAndAccount(bankId, accountId)).thenReturn(transactionList);
        TransactionService service = new TransactionService(mockOpenBankClient);

        List<Transaction> transactions = service.getTransactions();

        assertNotNull(transactions);
        return transactions;
    }

    @Test
    public void sumAmountsByType_shouldSumAmountsOfTransactionsWithType() {
        String type = "DEBIT";
        BigDecimal expectedTotal = BigDecimal.valueOf(200.00);
        List<Transaction> expectedTransactions = Arrays.asList(new Transaction(), new Transaction());
        for (Transaction transaction : expectedTransactions) {
            transaction.setTransactionType(type);
            transaction.setInstructedAmount(BigDecimal.valueOf(50.00));
            transaction.setTransactionAmount(BigDecimal.valueOf(50.00));
        }

        OpenBankClient mockOpenBankClient = mock(OpenBankClient.class);

        when(mockOpenBankClient.getTransactionsByBankAndAccount(bankId, accountId)).thenReturn(expectedTransactions);
        TransactionService service = new TransactionService(mockOpenBankClient);

        when(mockOpenBankClient.getTransactionsByBankAndAccount("rbs", "saving-kids-john")).thenReturn(expectedTransactions);

        BigDecimal actualTotal = service.sumAmountsByType(type);

        assertEquals(expectedTotal, actualTotal);
    }
}
