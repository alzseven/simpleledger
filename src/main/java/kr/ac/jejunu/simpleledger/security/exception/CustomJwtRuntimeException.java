package kr.ac.jejunu.simpleledger.security.exception;

public class CustomJwtRuntimeException extends Exception {
    public CustomJwtRuntimeException() {
        super("Something wrong with token");
    }

    public CustomJwtRuntimeException(String message) {
        super(message);
    }
}
