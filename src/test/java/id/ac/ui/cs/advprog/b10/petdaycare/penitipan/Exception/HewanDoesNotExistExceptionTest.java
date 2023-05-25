package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Exception;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.HewanDoesNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HewanDoesNotExistExceptionTest {

    @Test
    void testExceptionMessage() {
        Integer hewanId = 123;
        HewanDoesNotExistException exception =
                new HewanDoesNotExistException(hewanId);

        String expectedMessage = "Hewan with id " + hewanId + " does not exist";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
