package com.voa.goodbam.controller;

import com.voa.goodbam.support.authentication.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    private final JwtService jwtService;

    public AuthController(JwtService jwtService)
    {
        this.jwtService = jwtService;
    }

    @PostMapping("refresh")
    public ResponseEntity renewJwtToken(@RequestHeader("Authorization") String refreshToken)
    {
        return new ResponseEntity(jwtService.renewJwtToken(refreshToken),HttpStatus.OK);
    }
}