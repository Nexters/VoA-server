package com.voa.goodbam.user.domain.dto;

import com.voa.goodbam.config.security.jwt.JwtToken;
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
