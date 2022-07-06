package com.avedex.cc.config;

import com.avedex.cc.entity.TransactionAccountInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TransactionAccountConfig {
    private List<TransactionAccountInfo> transactionAccountInfos;

    public TransactionAccountConfig() {
        List<TransactionAccountInfo> transactionAccountInfos = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\李惠权\\IdeaProjects\\avedex-ftd\\src\\main\\resources\\ftc.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String context = sc.nextLine();
                String[] split = context.split(",");
                TransactionAccountInfo transactionAccountInfo = new TransactionAccountInfo();
                transactionAccountInfo.setArea(split[0]);
                transactionAccountInfo.setCommunity(split[1]);
                transactionAccountInfo.setAccount(split[2]);
                transactionAccountInfos.add(transactionAccountInfo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.transactionAccountInfos = transactionAccountInfos;
    }

    public List<TransactionAccountInfo> getTransactionAccountInfos() {
        return transactionAccountInfos;
    }
}
