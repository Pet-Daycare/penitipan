package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class PenitipanDoesnotException extends RuntimeException {

    public PenitipanDoesnotException(Integer id) {
        super("Penitipan with id " + id + " does not exist");
    }


}
