package com.example.springgooglelogin.controller;

import com.example.springgooglelogin.service.GoogleTokenAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class GoogleTokenAuthController {
    private final GoogleTokenAuthService googleTokenAuthService;

    @PostMapping("/token/google")
    public void googleLogin(@RequestBody String accessToken) {
        googleTokenAuthService.socialLoginWithToken(accessToken);
    }
}