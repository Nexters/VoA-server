package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.LoginRequest;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.domain.user.UserService;
import com.voa.goodbam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/new")
    public User newUser(@RequestParam String kakaoId,
                        @RequestParam String name,
                        @RequestParam Boolean isAppUser) {

        return userRepository.save(User.builder().kakaoId(kakaoId).name(name).isAppUser(isAppUser).build());
    }

    @PostMapping("/login")
    public ResponseEntity login(LoginRequest loginRequest, @RequestHeader("Platform") String platform) {
        return new ResponseEntity(userService.login(loginRequest, platform), HttpStatus.OK);
    }
}
