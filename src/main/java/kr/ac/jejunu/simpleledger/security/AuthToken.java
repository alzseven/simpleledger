package kr.ac.jejunu.simpleledger.security;

import kr.ac.jejunu.simpleledger.security.exception.CustomJwtRuntimeException;

public interface AuthToken<T> {
    boolean validate() throws CustomJwtRuntimeException;
    T getData() throws CustomJwtRuntimeException;
}