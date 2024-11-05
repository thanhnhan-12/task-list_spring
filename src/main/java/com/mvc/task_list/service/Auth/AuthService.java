package com.mvc.task_list.service.Auth;

import java.util.Map;

import com.mvc.task_list.dto.LoginDto;
import com.mvc.task_list.dto.RegisterDto;
import com.mvc.task_list.model.User;

public interface AuthService {
    User registUser(RegisterDto registerDto);

    String generateVerificationCode();

    void sendVerificationEmail(String email, String code);

    boolean verifyEmail(String email, String code);

    Map<String, Object> loginUser(LoginDto loginDto);
}
