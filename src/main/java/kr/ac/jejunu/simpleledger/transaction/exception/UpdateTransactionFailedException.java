package kr.ac.jejunu.simpleledger.transaction.exception;

public class UpdateTransactionFailedException extends Exception {
    public UpdateTransactionFailedException() {
        super("Update failed");
    }

    public UpdateTransactionFailedException(String message) {
        super(message);
    }
}
