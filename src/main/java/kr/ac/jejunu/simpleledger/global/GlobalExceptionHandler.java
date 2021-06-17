package kr.ac.jejunu.simpleledger.global;

import kr.ac.jejunu.simpleledger.security.exception.CustomAuthenticationException;
import kr.ac.jejunu.simpleledger.user.exception.ExistingEmailException;
import kr.ac.jejunu.simpleledger.user.exception.ExistingUserNameException;
import kr.ac.jejunu.simpleledger.user.exception.LoginFailedException;
import kr.ac.jejunu.simpleledger.user.exception.SignUpFailedException;
import org.springframework.http.HttpStatus;
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
    protected ResponseEntity<?> handleExistingEmailException(ExistingEmailException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}