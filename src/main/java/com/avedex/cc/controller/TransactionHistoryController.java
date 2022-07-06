package com.avedex.cc.controller;

import com.avedex.cc.entity.TransactionHistoryInfo;
import com.avedex.cc.properties.TransactionProperties;
import com.avedex.cc.service.TransactionHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        transactionHistoryService.exportTransactionHistories(transactionProperties.getFtd(), httpServletResponse);
    }

    @GetMapping("/v1/ave-dex/ftc/tx")
    public List<TransactionHistoryInfo> getFtcTransactionHistories() {
        List<TransactionHistoryInfo> transactionHistories = transactionHistoryService.getTransactionHistories(transactionProperties.getFtd());
        return transactionHistories;
    }


    @GetMapping("/v1/ave-dex/ftc/tx/export")
    public void exportFtcTransactionHistories(HttpServletResponse httpServletResponse) {
        transactionHistoryService.exportTransactionHistories(transactionProperties.getFtd(), httpServletResponse);
    }

    @GetMapping("/v1/ave-dex/tx")
    public List<TransactionHistoryInfo> getTransactionHistories(String txName) {
        List<TransactionHistoryInfo> transactionHistories = transactionHistoryService.getTransactionHistories(txName);
        return transactionHistories;
    }

    @GetMapping("/v1/ave-dex/export")
    public void exportTransactionHistories(String txName, HttpServletResponse httpServletResponse) {
        transactionHistoryService.exportTransactionHistories(txName, httpServletResponse);
    }


    @GetMapping("/v1/ave-dex/ftd/task")
    public void generatorFtdTransactionHistories() {
        transactionHistoryService.generatorTransactionHistories(transactionProperties.getFtd());
    }

    @GetMapping("/v1/ave-dex/ftc/task")
    public void generatorFtcTransactionHistories() {
        transactionHistoryService.generatorTransactionHistories(transactionProperties.getFtc());
    }
}
