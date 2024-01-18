package com.development.api.domain.transactional.client;


import com.development.api.domain.transactional.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("${openbank.sandbox.url}")
public interface OpenBankClient {

    @GetMapping("/banks/{bankId}/accounts/{accountId}/public/transactions")
    List<Transaction> getTransactionsByBankAndAccount(@PathVariable String bankId, @PathVariable String accountId);
}
