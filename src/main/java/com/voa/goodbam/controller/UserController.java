package com.voa.goodbam.controller;

import com.voa.goodbam.domain.room.User;
import com.voa.goodbam.domain.room.UserStatusInRoom;
import com.voa.goodbam.repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepsitory userRepsitory;

    @PostMapping("/new")
    public User newUser(@RequestParam String kakaoId,
                        @RequestParam String name,
                        @RequestParam boolean isAppUser,
                        @RequestParam String pushCode,
                        @RequestParam int uuid,
                        @RequestParam String os) {
        userRepsitory.save(User.create(kakaoId, name, isAppUser, pushCode, uuid, os));
        return null;
    }

}
