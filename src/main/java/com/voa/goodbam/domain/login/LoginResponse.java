package com.voa.goodbam.domain.login;

import com.voa.goodbam.support.authentication.JwtToken;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private JwtToken jwt;
    private String userName;
    private Long userId;
    private String profileURL;
}
