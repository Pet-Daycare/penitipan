package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class PenitipanDoesNotExistException extends RuntimeException {

    public PenitipanDoesNotExistException(Integer id) {
        super("Penitipan with id " + id + " does not exist");
    }


}
