package com.sam.library.gateway.controller;

import com.sam.library.gateway.dto.LoginRequestDTO;
import com.sam.library.gateway.dto.RegisterRequestDTO;
import com.sam.library.gateway.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Auth", description = "Auth services APIs")
public class AuthContoller {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO req) {
        Object res = authService.login(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO req) {
        Object res = authService.register(req);
        return ResponseEntity.ok(res);
    }
}
