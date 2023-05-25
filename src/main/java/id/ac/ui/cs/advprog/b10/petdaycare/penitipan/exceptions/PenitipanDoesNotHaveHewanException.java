package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class PenitipanDoesNotHaveHewanException extends RuntimeException {

    public PenitipanDoesNotHaveHewanException(Integer id) {
        super("Penitipan with id " + id + " does not have hewan");
    }


}
