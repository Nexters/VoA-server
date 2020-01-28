package com.voa.goodbam.controller;

import com.voa.goodbam.domain.room.User;
import com.voa.goodbam.repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepsitory userRepsitory;

    @PostMapping("/new")
    public User newUser(User user) {
        userRepsitory.save(User.create(user));
        return null;
    }

}
