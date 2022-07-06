package com.avedex.cc.api;

public interface TransactionApi {
    /**
     * 查询交易历史
     */
    String TRANSACTION_HISTORY_API = "https://api.opencc.xyz/v1api/v2/pairs/{txName}/txs";
    /**
     * 查询交易详情
     */
    String TRANSACTION_INFO_API = "https://apilist.tronscanapi.com/api/transaction-info?hash={transaction}";
}
