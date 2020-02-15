package com.voa.goodbam.domain.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequest {
    String kakaoToken;
    String userName;
}
