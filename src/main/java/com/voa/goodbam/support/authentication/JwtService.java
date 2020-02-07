package com.voa.goodbam.support.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class JwtService
{
    @Value("${JWT.ISSUER}")
    private String ISSUER;

    @Value("${JWT.ACCESS_SECRET}")
    private String ACCESS_SECRET;

    @Value("${JWT.REFRESH_SECRET}")
    private String REFRESH_SECRET;

    private final UserRepository userRepository;

    public JwtService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public JwtToken generateToken(String kakaoId)  {
        long nowTime = System.currentTimeMillis() / 1000;
        Token accessToken = generateAccessToken(kakaoId, JwtExpireTermEnum.ACCESS, nowTime);
        Token refreshToken = generateRefreshToken(kakaoId, JwtExpireTermEnum.REFRESH, nowTime);
        return new JwtToken(accessToken, refreshToken);
    }

    public DefaultResponse renewJwtToken(String refreshToken)
    {
        long nowTime = System.currentTimeMillis() / 1000;

        try
        {
            String kakaoId = decodeRefreshToken(refreshToken);

            if(kakaoId == null)
            {
                return DefaultResponse.of(StatusCode.UNAUTHORIZED, Message.AUTH_FAIL);
            }

            if(userRepository.findUserByKakaoId(kakaoId).isPresent())
            {
                return DefaultResponse.of(StatusCode.CREATED, Message.AUTH_SUCCESS, generateToken(kakaoId));
            }
            else
            {
                return DefaultResponse.of(StatusCode.UNAUTHORIZED, Message.AUTH_FAIL);
            }
        }
        catch (Exception e)
        {
            return DefaultResponse.of(StatusCode.UNAUTHORIZED, Message.AUTH_FAIL);
        }
    }

    private Token generateAccessToken(String kakaoId, JwtExpireTermEnum type, long nowTime)
    {
        long expireTime = nowTime + type.getExpireTerm();

        Date expireDate = new Date(expireTime * 1000);

        String data = JWT.create()
                .withExpiresAt(expireDate)
                .withIssuer(ISSUER)
                .withClaim("kakaoId", kakaoId)
                .sign(Algorithm.HMAC256(ACCESS_SECRET));

        return new Token(data, nowTime, expireTime);
    }

    private Token generateRefreshToken(String kakaoId, JwtExpireTermEnum type, long nowTime)
    {
        long expireTime = nowTime + type.getExpireTerm();

        Date expireDate = new Date(expireTime * 1000);

        String data = JWT.create()
                .withExpiresAt(expireDate)
                .withIssuer(ISSUER)
                .withClaim("kakaoId", kakaoId)
                .sign(Algorithm.HMAC256(REFRESH_SECRET));

        return new Token(data, nowTime, expireTime);
    }

    public String decodeAccessToken(String token)
    {
        try
        {
            final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(ACCESS_SECRET)).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT.getClaim("kakaoId").asString();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public String decodeRefreshToken(String token)
    {
        try
        {
            final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(REFRESH_SECRET)).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT.getClaim("kakaoId").asString();
        }
        catch (Exception e)
        {
            return null;
        }
    }
}