package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@SpringBootTest
public class MailSenderTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testSendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("vcy.maulana@gmail.com");
            message.setSubject("Test email");
            message.setText("This is a test email");
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}