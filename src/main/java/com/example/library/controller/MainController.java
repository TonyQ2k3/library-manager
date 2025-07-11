package com.example.library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://libba-frontend")
@RequestMapping("/api")
public class MainController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }


}
