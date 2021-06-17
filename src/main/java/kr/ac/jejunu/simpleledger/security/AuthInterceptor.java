package kr.ac.jejunu.simpleledger.security;

import kr.ac.jejunu.simpleledger.security.exception.CustomAuthenticationException;
import kr.ac.jejunu.simpleledger.security.exception.NoTokenException;
import kr.ac.jejunu.simpleledger.user.model.User;
import kr.ac.jejunu.simpleledger.user.repository.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final UserDao userDao;
    private static final String AUTHORIZATION_HEADER = "Bearer";

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {

        Optional<String> token = resolveToken(servletRequest);

        if (servletRequest.getMethod().equals("OPTIONS")) {
            return true;
        }

        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());
            Optional<User> user = userDao.findByUsername(jwtAuthTokenProvider.getUsernameFromToken(jwtAuthToken.getToken()));
            if(jwtAuthToken.validate() & user.isPresent()){
                return true;
            }
            else {
                throw new CustomAuthenticationException();
            }
        } else {
            throw new NoTokenException();
        }
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        String authToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(authToken)) {
            return Optional.of(authToken);
        } else {
            return Optional.empty();
        }
    }

}