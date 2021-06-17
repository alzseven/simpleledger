package kr.ac.jejunu.simpleledger.user.service;

import kr.ac.jejunu.simpleledger.security.AuthToken;
import kr.ac.jejunu.simpleledger.user.dto.LoginDto;
import kr.ac.jejunu.simpleledger.user.dto.SignupDto;
import kr.ac.jejunu.simpleledger.user.exception.ExistingUserNameException;
import kr.ac.jejunu.simpleledger.user.exception.LoginFailedException;
import kr.ac.jejunu.simpleledger.user.model.User;
import io.jsonwebtoken.Claims;
import kr.ac.jejunu.simpleledger.user.exception.ExistingEmailException;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService {
    Optional<User> signUp(SignupDto signupDto) throws ExistingEmailException, ExistingUserNameException, NoSuchAlgorithmException;
    Optional<User> login(LoginDto loginDto) throws LoginFailedException, NoSuchAlgorithmException;
    AuthToken<Claims> createAuthToken(User user);
}
