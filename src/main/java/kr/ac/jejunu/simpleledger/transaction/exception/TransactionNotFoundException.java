package kr.ac.jejunu.simpleledger.transaction.exception;

public class TransactionNotFoundException extends Exception {
    public TransactionNotFoundException() {
        super("There's no transaction");
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
