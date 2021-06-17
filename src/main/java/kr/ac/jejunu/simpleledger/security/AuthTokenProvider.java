package kr.ac.jejunu.simpleledger.security;

import java.util.Date;

public interface AuthTokenProvider<T> {
    T createAuthToken(String id, Date expiredDate);
    T convertAuthToken(String token);
}