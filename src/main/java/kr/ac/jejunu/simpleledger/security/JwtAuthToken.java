package kr.ac.jejunu.simpleledger.security;

import io.jsonwebtoken.*;
import kr.ac.jejunu.simpleledger.security.exception.CustomJwtRuntimeException;
import lombok.Getter;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

public class JwtAuthToken implements AuthToken<Claims> {

    @Getter
    private final String token;
    private final Key key;

    JwtAuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    JwtAuthToken(String id, Date expiredDate, Key key) {
        this.key = key;
        this.token = createJwtAuthToken(id, expiredDate).get();
    }

    @Override
    public boolean validate() throws CustomJwtRuntimeException {
        return getData() != null;
    }

    @Override
    public Claims getData() throws CustomJwtRuntimeException {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new CustomJwtRuntimeException();
        }
    }

    private Optional<String> createJwtAuthToken(String id, Date expiredDate) {

        var token = Jwts.builder()
                .setSubject(id)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate)
                .compact();

        return Optional.ofNullable(token);
    }
}
