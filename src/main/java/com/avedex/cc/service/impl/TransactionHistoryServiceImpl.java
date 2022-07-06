package com.avedex.cc.service.impl;

import com.avedex.cc.api.TransactionApi;
import com.avedex.cc.converters.FromSymbolConverter;
import com.avedex.cc.entity.TransactionAccountInfo;
import com.avedex.cc.entity.TransactionHistoryApiResult;
import com.avedex.cc.entity.TransactionHistoryInfo;
import com.avedex.cc.properties.TransactionProperties;
import com.avedex.cc.service.SystemEmailService;
import com.avedex.cc.service.TransactionHistoryService;
import com.avedex.cc.service.TransactionInfoService;
import com.avedex.cc.utils.DateTimeFormatUtil;
import com.avedex.cc.utils.ExcelUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final static String X_AUTH = "x-auth";
    @Resource
    private TransactionProperties transactionProperties;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private TransactionInfoService transactionInfoService;
    @Resource
    private SystemEmailService systemEmailService;

    @Override
    public List<TransactionHistoryInfo> getTransactionHistories(String txName) {
        String transactionHistoryApi = TransactionApi.TRANSACTION_HISTORY_API.replace("{txName}", txName);
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_AUTH, transactionProperties.getXAuthToken());
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(null, headers);
        ResponseEntity<String> restTemplateForObject = restTemplate.exchange(transactionHistoryApi, HttpMethod.GET, httpEntity, String.class);
        List<TransactionHistoryInfo> transactionHistories = Collections.emptyList();
        try {
            TransactionHistoryApiResult transactionHistoryApiResult = objectMapper.readValue(restTemplateForObject.getBody(), TransactionHistoryApiResult.class);
            String data = transactionHistoryApiResult.getData().replace("\r|\n", "");
            transactionHistories = objectMapper.readValue(data, new TypeReference<List<TransactionHistoryInfo>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (TransactionHistoryInfo transactionHistoryInfo : transactionHistories) {
            if (transactionHistoryInfo.getFrom_symbol().equalsIgnoreCase(FromSymbolConverter.FTD)) {
                transactionHistoryInfo.setAmount(transactionHistoryInfo.getFrom_amount());
            } else if (transactionHistoryInfo.getFrom_symbol().equalsIgnoreCase(FromSymbolConverter.WTRX)) {
                transactionHistoryInfo.setAmount(transactionHistoryInfo.getTo_amount());
            }
            TransactionAccountInfo transactionInfo = transactionInfoService.getTransactionInfo(transactionHistoryInfo.getTransaction());
            transactionHistoryInfo.setAccount(transactionInfo.getAccount());
            transactionHistoryInfo.setArea(transactionInfo.getArea());
            transactionHistoryInfo.setCommunity(transactionHistoryInfo.getCommunity());
        }
        return transactionHistories;
    }

    @Override
    public void exportTransactionHistories(String txName, HttpServletResponse httpServletResponse) {
        List<TransactionHistoryInfo> transactionHistories = this.getTransactionHistories(txName);
        String nowDateTime = DateTimeFormatUtil.formatToString(LocalDateTime.now());
        ExcelUtils.writeExcel(httpServletResponse, TransactionHistoryInfo.class, txName + "交易历史" + nowDateTime, transactionHistories);
    }

    @Override
    public void generatorTransactionHistories(String txName) {
        List<TransactionHistoryInfo> transactionHistories = this.getTransactionHistories(txName);
        String format = "yyyy年MM月dd日HH时";
        String nowDateTime = DateTimeFormatUtil.formatToString(LocalDateTime.now(), format);
        File file = new File(txName + "交易历史" + nowDateTime);
        if (!file.exists()) {
            file.mkdirs();
        }
        ExcelUtils.writeExcelFile(file, TransactionHistoryInfo.class, transactionHistories);
        String subject = "【" + txName + "当日成交历史(截止" + nowDateTime + ")】";
        systemEmailService.sendEmail(subject, transactionProperties.getTo(), subject + "详情随附件！", file.getName(), file);
    }

}
