package com.development.api.domain.transactional.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    private Long id;

    private String accountId;

    private String counterpartyAccount;

    private BigDecimal instructedAmount;

    private String instructedCurrency;

    private BigDecimal transactionAmount;

    private String transactionCurrency;

    private String counterpartyName;

    private String counterpartyLogoPath;

    private String transactionType;

    private String description;

    private LocalDateTime timestamp;


}

