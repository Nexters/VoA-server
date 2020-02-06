package com.voa.goodbam.user.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    String kakaoToken;
    String userName;
    boolean isAppUser;
}
