package com.avedex.cc.controller;

import com.avedex.cc.entity.TransactionHistoryInfo;
import com.avedex.cc.properties.TransactionProperties;
import com.avedex.cc.service.TransactionHistoryService;
import com.avedex.cc.utils.DateTimeFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@RestController
public class TransactionHistoryController {
    @Resource
    private TransactionHistoryService transactionHistoryService;
    @Resource
    private TransactionProperties transactionProperties;

    @GetMapping("/v1/ave-dex/ftd/tx")
    public List<TransactionHistoryInfo> getFtdTransactionHistories() {
        List<TransactionHistoryInfo> transactionHistories = transactionHistoryService.getTransactionHistories(transactionProperties.getFtd());
        return transactionHistories;
    }

    @GetMapping("/v1/ave-dex/ftd/tx/export")
    public void exportFtdTransactionHistories(HttpServletResponse httpServletResponse) {
        transactionHistoryService.exportTransactionHistories("FTD", transactionProperties.getFtd(), httpServletResponse);
    }

    @GetMapping("/v1/ave-dex/ftc/tx/export")
    public void exportFtcTransactionHistories(HttpServletResponse httpServletResponse) {
        transactionHistoryService.exportTransactionHistories("FTC", transactionProperties.getFtd(), httpServletResponse);
    }

    @GetMapping("/v1/ave-dex/ftc/tx")
    public List<TransactionHistoryInfo> getFtcTransactionHistories() {
        List<TransactionHistoryInfo> transactionHistories = transactionHistoryService.getTransactionHistories(transactionProperties.getFtd());
        return transactionHistories;
    }




    @GetMapping("/v1/ave-dex/tx")
    public List<TransactionHistoryInfo> getTransactionHistories(String txName) {
        List<TransactionHistoryInfo> transactionHistories = transactionHistoryService.getTransactionHistories(txName);
        return transactionHistories;
    }

    @GetMapping("/v1/ave-dex/export")
    public void exportTransactionHistories(String txName, HttpServletResponse httpServletResponse) {
        transactionHistoryService.exportTransactionHistories(null, txName, httpServletResponse);
    }


    @GetMapping("/v1/ave-dex/ftd/task")
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void generatorFtdTransactionHistories() {
        log.info("generatorFtdTransactionHistories {}", DateTimeFormatUtil.formatToString(LocalDateTime.now()));
        transactionHistoryService.generatorTransactionHistories("FTD", transactionProperties.getFtd());
    }

    @GetMapping("/v1/ave-dex/ftc/task")
    public void generatorFtcTransactionHistories() {
        transactionHistoryService.generatorTransactionHistories("FTC", transactionProperties.getFtc());
    }
}
