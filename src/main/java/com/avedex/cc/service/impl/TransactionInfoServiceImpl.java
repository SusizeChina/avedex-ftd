package com.avedex.cc.service.impl;

import com.avedex.cc.api.TransactionApi;
import com.avedex.cc.config.TransactionAccountConfig;
import com.avedex.cc.entity.TransactionAccountInfo;
import com.avedex.cc.service.TransactionInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;
@Service
public class TransactionInfoServiceImpl implements TransactionInfoService {
    @Resource
    private TransactionAccountConfig transactionAccountConfig;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public TransactionAccountInfo getTransactionInfo(String transactionId) {
        TransactionAccountInfo accountInfo = new TransactionAccountInfo();
        String transactionApi = TransactionApi.TRANSACTION_INFO_API.replace("{transaction}", transactionId);
        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity = new HttpEntity<MultiValueMap<String, Object>>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(transactionApi, HttpMethod.GET, multiValueMapHttpEntity, String.class);
        String body = responseEntity.getBody();
        try {
            Map map = objectMapper.readValue(body, Map.class);
            accountInfo.setAccount(map.get("ownerAddress").toString());
            for (TransactionAccountInfo transactionAccountInfo : transactionAccountConfig.getTransactionAccountInfos()) {
                if (transactionAccountInfo.getAccount().equalsIgnoreCase(accountInfo.getAccount())) {
                    accountInfo.setArea(transactionAccountInfo.getArea());
                    accountInfo.setCommunity(transactionAccountInfo.getCommunity());
                    continue;
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return accountInfo;
    }
}
