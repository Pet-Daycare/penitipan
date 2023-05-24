package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.exceptions;

public class CustomerDoesNotExistException extends RuntimeException  {

    public CustomerDoesNotExistException(String username) {
        super("Customer with id " + username + " does not exist");
    }
}

