package com.wizz.treehole;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author liqiqi_tql
 * @date 2020/10/12 -14:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {

    @Resource
    private JavaMailSender javaMailSender;

    @Test
    public void sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("1099462011@qq.com");
        msg.setTo("2721609557@qq.com");
//        抄送给其他人
//        msg.setCc("abc@126.com","def@126.com");
//        邮件名称
        msg.setSubject("程序新视界");
//        邮件内容
        msg.setText("技术分享");
        javaMailSender.send(msg);
    }
//一个发送邮件自动跳转到本博客的demo
    @Test
    public void sendHtmlMail() {
        String content="<html>\n" +
                "<body>\n" +
                "    <a href=\"https://yangxiangrui.site\">\n" +
                "    <img border=\"0\" src=\"https://edu-1014.oss-cn-beijing.aliyuncs.com/TIM%E5%9B%BE%E7%89%8720200629225320.jpg\" />\n" +
                "</a>\n" +
                "</body>\n" +
                "</html>";

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            // 第二个参数true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("1099462011@qq.com");
            helper.setTo("2721609557@qq.com");
            helper.setSubject("程序新视界");
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e){
            System.out.println("发送邮件异常");
        }
    }
}
