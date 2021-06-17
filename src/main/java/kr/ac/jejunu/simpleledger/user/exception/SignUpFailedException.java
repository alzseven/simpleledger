package kr.ac.jejunu.simpleledger.user.exception;

public class SignUpFailedException extends Exception {
    public SignUpFailedException() {
        super("Signup failed");
    }

    public SignUpFailedException(String message) {
        super(message);
    }
}
