package Exceptions;

public class CouldNotChangePasswordException extends RuntimeException {
    public CouldNotChangePasswordException() {
    }

    public CouldNotChangePasswordException(String message) {
        super(message);
    }
}
