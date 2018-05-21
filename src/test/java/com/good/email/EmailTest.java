package com.good.email;

import com.good.RootApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

public class EmailTest extends RootApplicationTests {

    @Resource
    private JavaMailSender mailSender; //自动注入的Bean

    @Value("${spring.mail.username}")
    private String sender; //读取配置文件中的参数

    @Test
    public void sendSimpleMail() throws Exception {
        String receiver = "cuipeng@enmonster.com";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);  //发送者
        message.setTo(receiver); //接收者
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }


    @Test
    public void sendMimeMMail() throws Exception {
        String receiver = "cuipeng@enmonster.com";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(sender);
        helper.setTo(receiver);
        helper.setSubject("这里是标题(带多个附件）!");
        helper.setText("这里是内容(带附件）");

        //添加两个附件（附件位置位于java-->resources目录)，可根据需要添加或修改
        ClassPathResource image = new ClassPathResource("/coupon.jpg");
        helper.addAttachment("Coupon.png", image);
        this.mailSender.send(mimeMessage);
    }
}