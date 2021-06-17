package kr.ac.jejunu.simpleledger.user.controller;

import kr.ac.jejunu.simpleledger.global.dto.JwtResponse;
import kr.ac.jejunu.simpleledger.user.dto.LoginDto;
import kr.ac.jejunu.simpleledger.user.dto.SignupDto;
import kr.ac.jejunu.simpleledger.user.exception.ExistingUserNameException;
import kr.ac.jejunu.simpleledger.user.exception.LoginFailedException;
import kr.ac.jejunu.simpleledger.user.exception.SignUpFailedException;
import kr.ac.jejunu.simpleledger.user.model.User;
import kr.ac.jejunu.simpleledger.security.JwtAuthToken;
import kr.ac.jejunu.simpleledger.user.service.UserService;
import kr.ac.jejunu.simpleledger.user.exception.ExistingEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupDto signupDto)
            throws SignUpFailedException, ExistingEmailException, ExistingUserNameException, NoSuchAlgorithmException {
        final Optional<User> user = userService.signUp(signupDto);
        if (user.isPresent()) {
            return ResponseEntity.ok("Successfully registered");
        } else {
            throw new SignUpFailedException();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto)
            throws LoginFailedException, NoSuchAlgorithmException {
        Optional<User> user = userService.login(loginDto);
        if (user.isPresent()) {
            JwtAuthToken jwtAuthToken = (JwtAuthToken) userService.createAuthToken(user.get());
            return ResponseEntity.ok(JwtResponse.builder()
                    .token(jwtAuthToken.getToken())
                    .username(user.get().getUsername())
                    .build());
        } else {
            throw new LoginFailedException();
        }
    }
}