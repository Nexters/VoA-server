package com.voa.goodbam.model.dto.user;

import lombok.Data;

@Data
public class CreateUserDto {
    private String kakaoId;
    private String name;
    private boolean isAppUser;
    private String pushCode;
    private int uuid;
    private String os;
}
