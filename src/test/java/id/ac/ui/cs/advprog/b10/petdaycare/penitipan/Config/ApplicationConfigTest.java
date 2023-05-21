package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Config;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.config.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationConfigTest {

    @Test
    public void testGetJavaMailSender() {
        ApplicationConfig config = new ApplicationConfig();

        JavaMailSender mailSender = config.getJavaMailSender();

        assertNotNull(mailSender);

        assertEquals("smtp.gmail.com", "smtp.gmail.com");
        assertEquals(587, 587);
        assertEquals("petdaycare.b10@gmail.com", "petdaycare.b10@gmail.com");
        assertEquals("ibyqedrbaximjxgg", "ibyqedrbaximjxgg");
    }
}
