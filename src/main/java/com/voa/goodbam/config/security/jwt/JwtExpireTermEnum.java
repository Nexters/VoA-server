package com.voa.goodbam.config.security.jwt;

import lombok.Getter;

@Getter
public enum JwtExpireTermEnum
{
    //6 hours
    ACCESS(3600*6),
    //24 hours
    REFRESH(3600*24);

    private long expireTerm;

    JwtExpireTermEnum(long expireTerm)
    {
        this.expireTerm = expireTerm;
    }
}