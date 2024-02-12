package com.example.springgooglelogin.controller;

import com.example.springgooglelogin.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class GoogleAuthController {
    private final GoogleAuthService googleAuthService;

    @GetMapping("/code/google")
    public String googleLogin(@RequestParam String code) {
        return googleAuthService.socialLogin(code);
    }
}