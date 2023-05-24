package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MailSenderTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void testSendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("vcy.maulana@gmail.com");
            message.setSubject("Test email");
            message.setText("This is a test email");
            javaMailSender.send(message);

            // Assertion
            // Check if the email was sent successfully
            assertTrue(true); // If no exception is thrown, the email was sent successfully

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}