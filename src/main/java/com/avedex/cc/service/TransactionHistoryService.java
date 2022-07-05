package com.avedex.cc.service;

import com.avedex.cc.entity.TransactionHistory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TransactionHistoryService {
    /**
     * 查询交易历史
     * @param name
     * @return
     */
    List<TransactionHistory> getTransactionHistory(String name);

    /**
     *
     * @param name
     * @param httpServletResponse
     */
    void exportTransactionHistory(String name, HttpServletResponse httpServletResponse);
}
