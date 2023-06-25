package nl.ordina.counter.Exceptions;

public class InvalidUserInput extends RuntimeException {
    public InvalidUserInput(String message) {
        super(message);
    }
}
