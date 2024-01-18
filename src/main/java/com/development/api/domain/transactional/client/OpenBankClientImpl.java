package com.development.api.domain.transactional.client;

import com.development.api.domain.transactional.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OpenBankClientImpl implements OpenBankClient {


    @Override
    public List<Transaction> getTransactionsByBankAndAccount(String bankId, String accountId) {
        return null;
    }


}
