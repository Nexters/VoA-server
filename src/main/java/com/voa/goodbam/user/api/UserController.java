package com.voa.goodbam.user.api;

import com.voa.goodbam.user.domain.dto.LoginRequest;
import com.voa.goodbam.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(final UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(LoginRequest loginRequest, @RequestHeader("Platform") String platform) {
        return new ResponseEntity(userService.login(loginRequest, platform), HttpStatus.OK);
    }
}
