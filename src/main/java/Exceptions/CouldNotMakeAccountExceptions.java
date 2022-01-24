package Exceptions;

public class CouldNotMakeAccountExceptions extends RuntimeException{
    public CouldNotMakeAccountExceptions() {
    }

    public CouldNotMakeAccountExceptions(String message) {
        super(message);
    }
}
