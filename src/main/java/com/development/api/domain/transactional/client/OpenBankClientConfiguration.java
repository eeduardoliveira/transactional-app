package com.development.api.domain.transactional.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenBankClientConfiguration {

    @Bean
    public OpenBankClient openBankClient() {
        return new OpenBankClientImpl();
    }
}