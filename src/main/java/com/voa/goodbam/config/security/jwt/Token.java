package com.voa.goodbam.config.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token
{
    private String data;
    private long now;
    private long expiredAt;
}