package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class PenitipanWithHewanIdDoesNotExistException extends RuntimeException {

    public PenitipanWithHewanIdDoesNotExistException(Integer id) {
        super("Penitipan with hewan with id " + id + " does not exist");
    }


}
