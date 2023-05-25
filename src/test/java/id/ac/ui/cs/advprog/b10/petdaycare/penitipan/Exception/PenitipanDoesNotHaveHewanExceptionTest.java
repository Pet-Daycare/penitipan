package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.Exception;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions.PenitipanDoesNotHaveHewanException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PenitipanDoesNotHaveHewanExceptionTest {

    @Test
    void testExceptionMessage() {
        Integer penitipanId = 789;
        PenitipanDoesNotHaveHewanException exception =
                new PenitipanDoesNotHaveHewanException(penitipanId);

        String expectedMessage = "Penitipan with id " + penitipanId + " does not have hewan";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
