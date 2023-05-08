package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class HewanDoesNotExistException extends RuntimeException {

    public HewanDoesNotExistException(Integer id) {
        super("Hewan with id " + id + " does not exist");
    }


}
