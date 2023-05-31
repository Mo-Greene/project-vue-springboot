package com.mogreene.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        log.info("Hello MoGreene!");
        log.info("SourceTree Test!");
        return "Hello World!";
    }
}
