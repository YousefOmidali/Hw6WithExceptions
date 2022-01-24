package Exceptions;

public class InvalidCardToCardServiceException extends RuntimeException {
    public InvalidCardToCardServiceException() {
    }

    public InvalidCardToCardServiceException(String message) {
        super(message);
    }
}
