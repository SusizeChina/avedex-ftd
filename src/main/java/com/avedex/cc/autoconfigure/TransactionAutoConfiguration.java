package com.avedex.cc.autoconfigure;

import com.avedex.cc.config.TransactionAccountConfig;
import com.avedex.cc.properties.TransactionProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TransactionProperties.class)
public class TransactionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean({RestTemplate.class})
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean({TransactionAccountConfig.class})
    public TransactionAccountConfig transactionAccountConfig() {
        return new TransactionAccountConfig();
    }

    @Bean
    @ConditionalOnMissingBean({ObjectMapper.class})
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
