package kr.ac.jejunu.simpleledger.transaction.exception;

public class DeleteTransactionFailedException extends Exception {
    public DeleteTransactionFailedException() {
        super("Delete failed");
    }

    public DeleteTransactionFailedException(String message) {
        super(message);
    }
}
