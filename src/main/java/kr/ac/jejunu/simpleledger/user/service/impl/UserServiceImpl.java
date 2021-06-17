package kr.ac.jejunu.simpleledger.user.service.impl;

import kr.ac.jejunu.simpleledger.global.util.SimplePasswordEncoder;
import kr.ac.jejunu.simpleledger.security.JwtAuthToken;
import kr.ac.jejunu.simpleledger.security.JwtAuthTokenProvider;
import kr.ac.jejunu.simpleledger.user.dto.LoginDto;
import kr.ac.jejunu.simpleledger.user.dto.SignupDto;
import kr.ac.jejunu.simpleledger.user.exception.ExistingEmailException;
import kr.ac.jejunu.simpleledger.user.exception.ExistingUserNameException;
import kr.ac.jejunu.simpleledger.user.model.User;
import kr.ac.jejunu.simpleledger.user.repository.UserDao;
import kr.ac.jejunu.simpleledger.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Value("${app.jwt.expirationtime}")
    private int LOGIN_RETENTION_MINUTES;

    public UserServiceImpl(UserDao userDao, JwtAuthTokenProvider jwtAuthTokenProvider) {
        this.userDao = userDao;
        this.jwtAuthTokenProvider = jwtAuthTokenProvider;
    }

    @Override
    public Optional<User> signUp(SignupDto signupDto) throws ExistingEmailException, ExistingUserNameException, NoSuchAlgorithmException {
        if (userDao.existsByEmail(signupDto.getEmail())) {
            throw new ExistingEmailException();
        }
        if (userDao.existsByUsername(signupDto.getEmail())) {
            throw new ExistingUserNameException();
        }

        String encryptedPassword = SimplePasswordEncoder.encode(signupDto.getPassword());

        User user = User.builder().username(signupDto.getUsername())
                .password(encryptedPassword)
                .email(signupDto.getEmail())
                .build();
        return Optional.of(userDao.save(user));
    }

    @Override
    public Optional<User> login(LoginDto loginDto) throws NoSuchAlgorithmException {
        String encryptedPassword = SimplePasswordEncoder.encode(loginDto.getPassword());
        return userDao.findByUsernameAndPassword(loginDto.getUsername(), encryptedPassword);
    }

    @Override
    public JwtAuthToken createAuthToken(User user) {
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return jwtAuthTokenProvider.createAuthToken(user.getUsername(), expiredDate);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
