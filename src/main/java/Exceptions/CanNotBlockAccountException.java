package Exceptions;

public class CanNotBlockAccountException extends RuntimeException{
    public CanNotBlockAccountException() {
    }

    public CanNotBlockAccountException(String message) {
        super(message);
    }
}
