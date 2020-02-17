package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.LoginRequest;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.user.User;
import com.voa.goodbam.domain.user.UserService;
import com.voa.goodbam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest, @RequestHeader("Platform") String platform) {
        System.out.println(loginRequest.toString());
        return new ResponseEntity(userService.login(loginRequest, platform), HttpStatus.OK);
    }

    @PutMapping("/token")
    public ResponseEntity updateFcmToken(@RequestBody Map<String, Object> req, @RequestHeader("Platform") String platform) {
        try {
            String fcmToken = req.get("fcmToken").toString();
            Long userId = Long.valueOf(req.get("userId").toString());
            User user = userRepository.findUserById(userId).get();
            user.setOs(platform);
            user.setFcmRegisterationToken(fcmToken);
            userRepository.save(user);
        }catch(Exception e){
            return new ResponseEntity(DefaultResponse.of(StatusCode.INTERNAL_SERVER_ERROR, Message.INTERNAL_SEVER_ERROR), HttpStatus.OK);
        }
        return new ResponseEntity(DefaultResponse.of(StatusCode.OK, Message.OK), HttpStatus.OK);
    }
}
