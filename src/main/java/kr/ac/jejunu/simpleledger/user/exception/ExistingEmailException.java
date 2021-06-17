package kr.ac.jejunu.simpleledger.user.exception;

public class ExistingEmailException extends Exception {
    public ExistingEmailException() {
        super("Email already exists");
    }

    public ExistingEmailException(String message) {
        super(message);
    }
}
