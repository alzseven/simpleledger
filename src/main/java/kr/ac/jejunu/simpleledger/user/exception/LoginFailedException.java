package kr.ac.jejunu.simpleledger.user.exception;

public class LoginFailedException extends Exception {
    public LoginFailedException() {
        super("Login failed");
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
