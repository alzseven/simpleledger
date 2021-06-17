package kr.ac.jejunu.simpleledger.security.exception;

public class NoTokenException extends Exception {
    public NoTokenException() {
        super("No token");
    }

    public NoTokenException(String message) {
        super(message);
    }
}
