package com.avedex.cc.service;

import java.io.File;

public interface SystemEmailService {
    /**
     * 发送邮件服务
     *
     * @param subject            邮件标题
     * @param to                 收件人
     * @param context            邮件正文
     * @param attachmentFilename 附件名称
     * @param attachmentFile     附件
     */
    void sendEmail(String subject,  String [] to, String context, String attachmentFilename, File attachmentFile);
}
