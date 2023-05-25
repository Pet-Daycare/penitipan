package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Exception;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanWithHewanIdDoesNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PenitipanWithHewanIdDoesNotExistExceptionTest {

    @Test
    void testExceptionMessage() {
        Integer hewanId = 123;
        PenitipanWithHewanIdDoesNotExistException exception =
                new PenitipanWithHewanIdDoesNotExistException(hewanId);

        String expectedMessage = "Penitipan with hewan with id " + hewanId + " does not exist";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
