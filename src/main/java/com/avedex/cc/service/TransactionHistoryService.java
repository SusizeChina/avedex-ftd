package com.avedex.cc.service;

import com.avedex.cc.entity.TransactionHistoryInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TransactionHistoryService {
    /**
     * 查询交易历史
     *
     * @param txName
     * @return
     */
    List<TransactionHistoryInfo> getTransactionHistories(String txName);

    /**
     * @param type
     * @param txName
     * @param httpServletResponse
     */
    void exportTransactionHistories(String type, String txName, HttpServletResponse httpServletResponse);

    /**
     * @param type
     * @param txName
     */
    void generatorTransactionHistories(String type, String txName);
}
