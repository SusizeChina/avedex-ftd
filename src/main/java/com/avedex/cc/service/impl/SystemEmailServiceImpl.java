package com.avedex.cc.service.impl;

import com.avedex.cc.service.SystemEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
@Slf4j
public class SystemEmailServiceImpl implements SystemEmailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private MailProperties mailProperties;

    @Override
    public void sendEmail(String subject, String [] to, String context, String attachmentFilename, File attachmentFile) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            // true表示构建一个可以带附件的邮件对象
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setText(context);
            mimeMessageHelper.addAttachment(attachmentFilename, attachmentFile);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("sendEmail error {}", e.getMessage());
        }
    }
}
