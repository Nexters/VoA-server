package com.voa.goodbam.controller;

import com.voa.goodbam.support.authentication.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
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