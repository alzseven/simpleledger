package kr.ac.jejunu.simpleledger.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupDto {
    private String username;
    private String password;
    private String email;

}