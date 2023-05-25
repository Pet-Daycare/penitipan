package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Exception;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PenitipanDoesNotExistExceptionTest {

    @Test
    void testExceptionMessage() {
        Integer penitipanId = 456;
        PenitipanDoesNotExistException exception =
                new PenitipanDoesNotExistException(penitipanId);

        String expectedMessage = "Penitipan with id " + penitipanId + " does not exist";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
