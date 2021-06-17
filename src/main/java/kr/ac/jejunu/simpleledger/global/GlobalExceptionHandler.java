package kr.ac.jejunu.simpleledger.global;

import kr.ac.jejunu.simpleledger.transaction.exception.*;
import kr.ac.jejunu.simpleledger.user.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // User
    @ExceptionHandler(SignUpFailedException.class)
    protected ResponseEntity<?> handleSignUpFailedException(SignUpFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ExistingUserNameException.class)
    protected ResponseEntity<?> handleExistingUserException(SignUpFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(LoginFailedException.class)
    protected ResponseEntity<?> handleLoginFailedException(LoginFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ExistingEmailException.class)
    protected ResponseEntity<?> handleExistingEmailException(ExistingEmailException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Transaction
    @ExceptionHandler(TransactionNotFoundException.class)
    protected ResponseEntity<?> handleTransactionNotFoundException(TransactionNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    // TODO:
    @ExceptionHandler(CreateTransactionFailedException.class)
    protected ResponseEntity<?> handleCreateTransactionFailedException(CreateTransactionFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DeleteTransactionFailedException.class)
    protected ResponseEntity<?> handleDeleteTransactionFailedException(DeleteTransactionFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UpdateTransactionFailedException.class)
    protected ResponseEntity<?> handleUpdateTransactionFailedException(UpdateTransactionFailedException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}