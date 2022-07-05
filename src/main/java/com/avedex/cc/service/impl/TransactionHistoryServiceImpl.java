package com.avedex.cc.service.impl;

import com.avedex.cc.api.TransactionHistoryApi;
import com.avedex.cc.entity.ApiResult;
import com.avedex.cc.entity.AreaInfo;
import com.avedex.cc.entity.TransactionHistory;
import com.avedex.cc.service.TransactionHistoryService;
import com.avedex.cc.utils.ExcelUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final static String X_AUTH = "x-auth";
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${x-auth-token:}")
    private String x_auth_token;

    @Override
    public List<TransactionHistory> getTransactionHistory(String name) {
        List<AreaInfo> areaInfos = new ArrayList<>();
        try {

            File file = new File("C:\\Users\\李惠权\\IdeaProjects\\avedex-ftd\\src\\main\\resources\\ftc.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String context = sc.nextLine();
                String[] split = context.split(",");
                AreaInfo areaInfo = new AreaInfo();
                areaInfo.setArea(split[0]);
                areaInfo.setName(split[1]);
                areaInfo.setAddress(split[2]);
                areaInfos.add(areaInfo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        RestTemplate restTemplate = new RestTemplate();
        String transactionHistoryApi = TransactionHistoryApi.TransactionHistoryApi.replace("{name}", name);
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_AUTH, x_auth_token);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(null, headers);
        ResponseEntity<String> restTemplateForObject = restTemplate.exchange(transactionHistoryApi, HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<TransactionHistory> transactionHistories = Collections.emptyList();
        try {
            ApiResult apiResult = objectMapper.readValue(restTemplateForObject.getBody(), ApiResult.class);
            String data = apiResult.getData().replace("\r|\n", "");
            transactionHistories = objectMapper.readValue(data, new TypeReference<List<TransactionHistory>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        for (TransactionHistory transactionHistory : transactionHistories
        ) {
            if (transactionHistory.getFrom_symbol().equalsIgnoreCase("FTD")) {
                transactionHistory.setAmount(transactionHistory.getFrom_amount());
            } else if (transactionHistory.getFrom_symbol().equalsIgnoreCase("WTRX")) {
                transactionHistory.setAmount(transactionHistory.getTo_amount());
            }

            String transaction = TransactionHistoryApi.TransactionHistoryInfoApi.replace("{transaction}", transactionHistory.getTransaction());
            HttpEntity<MultiValueMap<String, Object>> httpEntitys = new HttpEntity<MultiValueMap<String, Object>>(null, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(transaction, HttpMethod.GET, httpEntitys, String.class);
            String body = responseEntity.getBody();
            try {
                Map map = objectMapper.readValue(body, Map.class);
                transactionHistory.setAccount(map.get("ownerAddress").toString());
                for (AreaInfo areaInfo : areaInfos) {
                    if (areaInfo.getAddress().equalsIgnoreCase(transactionHistory.getAccount())) {
                        transactionHistory.setArea(areaInfo.getArea());
                        transactionHistory.setName(areaInfo.getName());
                        continue;
                    }
                }

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
        return transactionHistories;
    }

    @Override
    public void exportTransactionHistory(String name, HttpServletResponse httpServletResponse) {
        sendEmail();
//        List<TransactionHistory> transactionHistories = this.getTransactionHistory(name);
//        ExcelUtils.writeExcel(httpServletResponse, null, "交易历史", transactionHistories);
    }

    private void sendEmail() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // true表示构建一个可以带附件的邮件对象
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("交易记录");
            helper.setFrom("1725719680@qq.com");
            helper.setTo("276823471@qq.com");
            helper.setSentDate(new Date());
            helper.setText("以下为交易详情");
            helper.addAttachment("交易记录.xlsx", new File("C:\\Users\\李惠权\\Downloads\\交易历史 (6).xlsx"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
