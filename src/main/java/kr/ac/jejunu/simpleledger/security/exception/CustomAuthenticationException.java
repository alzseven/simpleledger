package kr.ac.jejunu.simpleledger.security.exception;

public class CustomAuthenticationException extends Exception {
    public CustomAuthenticationException() {
        super("No user with this principal");
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
