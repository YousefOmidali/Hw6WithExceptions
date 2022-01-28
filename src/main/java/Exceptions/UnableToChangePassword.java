package Exceptions;

public class UnableToChangePassword extends RuntimeException {
    public UnableToChangePassword() {
    }

    public UnableToChangePassword(String message) {
        super(message);
    }
}
