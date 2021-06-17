package kr.ac.jejunu.simpleledger.transaction.exception;

public class CreateTransactionFailedException extends Exception {
    public CreateTransactionFailedException() {
        super("Create transaction failed");
    }

    public CreateTransactionFailedException(String message) {
        super(message);
    }
}
