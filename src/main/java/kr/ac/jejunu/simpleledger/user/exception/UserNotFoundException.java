package kr.ac.jejunu.simpleledger.user.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("No user");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
