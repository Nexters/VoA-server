package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.LoginRequest;
import com.voa.goodbam.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(LoginRequest loginRequest, @RequestHeader("Platform") String platform) {
        return new ResponseEntity(userService.login(loginRequest, platform), HttpStatus.OK);
    }
}
