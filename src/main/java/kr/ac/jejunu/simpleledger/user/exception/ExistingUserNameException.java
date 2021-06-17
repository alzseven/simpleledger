package kr.ac.jejunu.simpleledger.user.exception;

public class ExistingUserNameException extends Exception {
    public ExistingUserNameException() {
        super("Username already exists");
    }

    public ExistingUserNameException(String message) {
        super(message);
    }
}
