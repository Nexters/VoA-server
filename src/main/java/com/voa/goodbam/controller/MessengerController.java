package com.voa.goodbam.controller;

import com.voa.goodbam.domain.login.DefaultResponse;
import com.voa.goodbam.domain.login.Message;
import com.voa.goodbam.domain.login.StatusCode;
import com.voa.goodbam.domain.messenger.Messenger;
import com.voa.goodbam.repository.MessengerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messenger")
@CrossOrigin("*")
public class MessengerController {
    private final MessengerRepository messengerRepository;

    public MessengerController(MessengerRepository messengerRepository) {
        this.messengerRepository = messengerRepository;
    }

    @PostMapping("/new")
    public ResponseEntity sendMessage(@RequestParam Long senderUserId, @RequestParam Long targetUserId, @RequestParam Long roomId) {
        return new ResponseEntity(DefaultResponse.of(StatusCode.CREATED, Message.OK, messengerRepository.save(Messenger.create(senderUserId, targetUserId, roomId))), HttpStatus.OK);
    }
}
