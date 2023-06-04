package com.mogreene.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 컨트롤러
 */
@Slf4j
@RestController
public class LoginController {

    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public void registerGet() {

        log.info("is Run???");
    }
}
