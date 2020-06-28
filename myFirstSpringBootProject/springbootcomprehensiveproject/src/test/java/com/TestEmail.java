package com;

import com.utils.MailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Random;

/**
 * @author 冉堃赤
 * @date 2020/3/8 14:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringbootApplication.class})
public class TestEmail {

    @Autowired
    private MailUtils mailUtils;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() {

        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            code.append(random.nextInt(10));
        }
        System.out.println(code.toString());

        String content = "您正在执行忘记密码的操作，您的验证码为" + code.toString();
        mailUtils.sendSimpleMail("2243756824@qq.com","清华大学", content);
    }

    @Test
    public void sendHtmlMail() throws MessagingException {
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<body>\n" +
                "</html>\n";
        mailUtils.sendHtmlMail("2243756824@qq.com","这是一封HTML邮件",content);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath = "";
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<h1>附件传输</h1>\n" +
                "<body>\n" +
                "</html>\n";
        mailUtils.sendAttachmentsMail("2243756824@qq.com","这是一封HTML邮件",content, filePath);
    }

    @Test
    public void sendInlinkResourceMail() throws MessagingException {
        //TODO 改为本地图片目录
        String imgPath = "/ijiangtao/img/blob/dd9899b4cf95cbf074ddc4607007046c022564cb/blog/animal/dog/dog-at-work-with-computer-2.jpg?raw=true";
        String rscId = "admxj001";
        String content = "<html>" +
                "<body>" +
                "<h3>hello world</h3>" +
                "<h1>html</h1>" +
                "<h1>图片邮件</h1>" +
                "<img src='cid:"+rscId+"'></img>" +
                "<body>" +
                "</html>";

        mailUtils.sendInLinkResourceMail("ispringboot@163.com","这是一封图片邮件",content, imgPath, rscId);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id","ispringboot");

        String emailContent = templateEngine.process("emailTeplate", context);
        mailUtils.sendHtmlMail("ispringboot@163.com","这是一封HTML模板邮件",emailContent);
    }
}
