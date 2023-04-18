package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class HewanDoesnotException extends RuntimeException {

    public HewanDoesnotException(Integer id) {
        super("Hewan with id " + id + " does not exist");
    }


}
