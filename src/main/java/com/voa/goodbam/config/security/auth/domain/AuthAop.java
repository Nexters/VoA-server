package com.voa.goodbam.config.security.auth.domain;

import com.voa.goodbam.common.response.DefaultResponse;
import com.voa.goodbam.common.response.Message;
import com.voa.goodbam.common.response.StatusCode;
import com.voa.goodbam.config.security.jwt.JwtService;
import com.voa.goodbam.user.domain.UserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Aspect
@Component
public class AuthAop
{
    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final HttpServletRequest httpServletRequest;

    public  AuthAop(UserRepository userRepository, JwtService jwtService, HttpServletRequest httpServletRequest)
    {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.httpServletRequest = httpServletRequest;
    }

    @Pointcut("@annotation(com.voa.goodbam.config.security.auth.domain.Auth)")
    public void auth(){}

    @Pointcut("!execution(* com.voa.goodbam.user.api.UserController.login(..))")
    public void login(){}

    @Pointcut("execution(* com..*Controller.*(..))")
    public void controllerMethod(){}

    @Around("login() && (auth() || controllerMethod())")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable
    {
        if(validToken() == false)
        {
            return new ResponseEntity(DefaultResponse.builder()
                    .statusCode(StatusCode.UNAUTHORIZED)
                    .message(Message.AUTH_FAIL)
                    .build(), HttpStatus.OK);
        }
        return pjp.proceed(pjp.getArgs());
    }

    public boolean validToken()
    {
        final String jwt = httpServletRequest.getHeader("Authorization");
        if(!Optional.ofNullable(jwt).isPresent())
        {
            return false;
        }

        final String kakaoId = jwtService.decodeAccessToken(jwt);
        if(kakaoId==null)
        {
            return false;
        }

        if(!userRepository.findUserByKakaoId(kakaoId).isPresent())
        {
            return false;
        }

        return true;
    }

}