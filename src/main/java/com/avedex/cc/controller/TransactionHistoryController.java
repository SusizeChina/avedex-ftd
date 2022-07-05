package com.avedex.cc.controller;

import com.avedex.cc.entity.TransactionHistory;
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

    @GetMapping("/v1/ave-dex/tx")
    public List<TransactionHistory> getTransactionHistory(String name) {
        name = "TRi5H2adh2h5jAsU4GES6HpQQPvn1G2f68-tron";
        List<TransactionHistory> transactionHistories = transactionHistoryService.getTransactionHistory(name);
        return transactionHistories;
    }

    @GetMapping("/v1/ave-dex/tx/export")
    public void exportTransactionHistory(String name, HttpServletResponse httpServletResponse) {
        name = "TRi5H2adh2h5jAsU4GES6HpQQPvn1G2f68-tron";
        transactionHistoryService.exportTransactionHistory(name, httpServletResponse);
    }
}