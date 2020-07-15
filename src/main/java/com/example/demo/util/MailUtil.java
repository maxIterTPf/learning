package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author big bug
 */
@Component
public class MailUtil {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    /**
     * 发送邮件
     *
     * @param to      收件人
     * @param subject 邮件主题
     * @param text    邮件内容
     * @return 返回异步数据 (可以把注解@Async删掉,返回值改成void)
     */
    @Async
    public Future<Boolean> send(String to, String subject, String text) {
        try {
            MimeMessage message = sender.createMimeMessage();
            message.setFrom(from);
            message.setHeader(MimeMessage.RecipientType.TO.toString(), to);
            message.setSubject(subject);
            message.setText(text);

            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
        return CompletableFuture.completedFuture(true);
    }

}
