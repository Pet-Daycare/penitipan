package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class OrderDoesNotExistException extends RuntimeException {
    public OrderDoesNotExistException(Integer id) {
        super("Order with id " + id + " does not exist");
    }
}
