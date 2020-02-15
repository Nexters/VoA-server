package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.LoginRequest;
import com.voa.goodbam.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest, @RequestHeader("Platform") String platform) {
        System.out.println(loginRequest.toString());
        return new ResponseEntity(userService.login(loginRequest, platform), HttpStatus.OK);
    }
}
