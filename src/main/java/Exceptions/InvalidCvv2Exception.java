package Exceptions;

public class InvalidCvv2Exception extends RuntimeException{
    public InvalidCvv2Exception() {
    }

    public InvalidCvv2Exception(String message) {
        super(message);
    }
}
