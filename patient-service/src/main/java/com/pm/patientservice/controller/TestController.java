package com.pm.patientservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public TestController() {
        System.out.println("TestController initialized!");
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello endpoint called!");
        return "Hello World!";
    }
}

