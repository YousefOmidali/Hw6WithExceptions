package Exceptions;

public class InvalidNationalCodeException extends RuntimeException{
    public InvalidNationalCodeException() {
    }

    public InvalidNationalCodeException(String message) {
        super(message);
    }
}
