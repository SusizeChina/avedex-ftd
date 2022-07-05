package com.avedex.cc.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.avedex.cc.converters.DateTimeConverter;
import com.avedex.cc.converters.SymbolConverter;


public class TransactionHistory extends BaseRowModel {

    @ExcelProperty(value = {"交易时间"}, index = 0, converter = DateTimeConverter.class)
    private int time;
    @ExcelProperty(value = {"交易类型"}, index = 1, converter = SymbolConverter.class)
    private String from_symbol;
    @ExcelProperty(value = {"交易金额"}, index = 2)
    private double amount;
    @ExcelProperty(value = {"账号地区"}, index = 3)
    private String area;
    @ExcelProperty(value = {"账号社区"}, index = 4)
    private String name;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getAmm() {
        return amm;
    }

    public void setAmm(String amm) {
        this.amm = amm;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public double getAmount_eth() {
        return amount_eth;
    }

    public void setAmount_eth(double amount_eth) {
        this.amount_eth = amount_eth;
    }

    public int getAmount_usd() {
        return amount_usd;
    }

    public void setAmount_usd(int amount_usd) {
        this.amount_usd = amount_usd;
    }

    public String getFrom_address() {
        return from_address;
    }

    public void setFrom_address(String from_address) {
        this.from_address = from_address;
    }

    public int getFrom_price_eth() {
        return from_price_eth;
    }

    public void setFrom_price_eth(int from_price_eth) {
        this.from_price_eth = from_price_eth;
    }

    public double getFrom_price_usd() {
        return from_price_usd;
    }

    public void setFrom_price_usd(double from_price_usd) {
        this.from_price_usd = from_price_usd;
    }

    public String getFrom_symbol() {
        return from_symbol;
    }

    public void setFrom_symbol(String from_symbol) {
        this.from_symbol = from_symbol;
    }

    public double getFrom_amount() {
        return from_amount;
    }

    public void setFrom_amount(double from_amount) {
        this.from_amount = from_amount;
    }

    public String getTo_address() {
        return to_address;
    }

    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public double getTo_price_eth() {
        return to_price_eth;
    }

    public void setTo_price_eth(double to_price_eth) {
        this.to_price_eth = to_price_eth;
    }

    public double getTo_price_usd() {
        return to_price_usd;
    }

    public void setTo_price_usd(double to_price_usd) {
        this.to_price_usd = to_price_usd;
    }

    public String getTo_symbol() {
        return to_symbol;
    }

    public void setTo_symbol(String to_symbol) {
        this.to_symbol = to_symbol;
    }

    public double getTo_amount() {
        return to_amount;
    }

    public void setTo_amount(double to_amount) {
        this.to_amount = to_amount;
    }

    public String getWallet_address() {
        return wallet_address;
    }

    public void setWallet_address(String wallet_address) {
        this.wallet_address = wallet_address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}