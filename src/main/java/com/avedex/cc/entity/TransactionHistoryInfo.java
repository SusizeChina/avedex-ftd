package com.avedex.cc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.avedex.cc.converters.FromSymbolConverter;
import com.avedex.cc.converters.TimestampConverter;
import lombok.Data;

@Data
public class TransactionHistoryInfo extends BaseRowModel {

    @ExcelProperty(value = {"交易时间"}, index = 0, converter = TimestampConverter.class)
    private int time;
    @ExcelProperty(value = {"交易类型"}, index = 1, converter = FromSymbolConverter.class)
    private String from_symbol;
    @ExcelProperty(value = {"交易金额"}, index = 2)
    private double amount;
    @ExcelProperty(value = {"账号地区"}, index = 3)
    private String area;
    @ExcelProperty(value = {"账号社区"}, index = 4)
    private String community;
    @ExcelProperty(value = {"交易账号"}, index = 5)
    private String account;
    @ExcelProperty(value = {"总价"}, index = 6)
    private int amount_usd;
    @ExcelIgnore
    private String transaction;
    @ExcelIgnore
    private String id;
    @ExcelIgnore
    private String chain;
    @ExcelIgnore
    private String amm;
    @ExcelIgnore
    private String sender;
    @ExcelIgnore
    private double amount_eth;
    @ExcelIgnore
    private String to_symbol;
    @ExcelIgnore
    private String from_address;
    @ExcelIgnore
    private int from_price_eth;
    @ExcelIgnore
    private double from_price_usd;
    @ExcelIgnore
    private double from_amount;
    @ExcelIgnore
    private String to_address;
    @ExcelIgnore
    private double to_price_eth;
    @ExcelIgnore
    private double to_price_usd;
    @ExcelIgnore
    private double to_amount;
    @ExcelIgnore
    private String wallet_address;

}
