package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Exception;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.ErrorTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

class ErrorTemplateTest {

    @Test
    void testErrorTemplate() {
        String message = "Error message";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ZonedDateTime timestamp = ZonedDateTime.now();

        ErrorTemplate errorTemplate = new ErrorTemplate(message, httpStatus, timestamp);

        Assertions.assertEquals(message, errorTemplate.message());
        Assertions.assertEquals(httpStatus, errorTemplate.httpStatus());
        Assertions.assertEquals(timestamp, errorTemplate.timestamp());
    }
}
