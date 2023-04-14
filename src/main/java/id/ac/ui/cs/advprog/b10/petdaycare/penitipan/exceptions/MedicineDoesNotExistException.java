package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class MedicineDoesNotExistException extends RuntimeException {

    public MedicineDoesNotExistException(Integer id) {
        super("Medicine with id " + id + " does not exist");
    }


}
