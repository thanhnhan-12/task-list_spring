package com.mvc.task_list.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.task_list.dto.LoginDto;
import com.mvc.task_list.dto.RegisterDto;
import com.mvc.task_list.model.User;
import com.mvc.task_list.service.Auth.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        User user = authService.registUser(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto) {
        Map<String, Object> authResponse = authService.loginUser(loginDto);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
