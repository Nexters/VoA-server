package com.voa.goodbam.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/")
    public String index() {

        return "home";
    }

}
