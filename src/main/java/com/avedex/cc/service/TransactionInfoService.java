package com.avedex.cc.service;

import com.avedex.cc.entity.TransactionAccountInfo;

public interface TransactionInfoService {
    /**
     * 查询交易历史
     *
     * @param name
     * @return
     */
    TransactionAccountInfo getTransactionInfo(String transaction);

}
