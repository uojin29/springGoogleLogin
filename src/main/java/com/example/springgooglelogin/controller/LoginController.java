package com.example.springgooglelogin.controller;

import com.example.springgooglelogin.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/code")
    public void googleLogin(@RequestParam String code) {
        loginService.socialLogin(code);
    }
}