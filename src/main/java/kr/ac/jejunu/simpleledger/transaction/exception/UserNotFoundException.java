package kr.ac.jejunu.simpleledger.transaction.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("No user");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
