package com.mvc.task_list.mapper;

import org.springframework.stereotype.Component;

import com.mvc.task_list.dto.LoginDto;
import com.mvc.task_list.model.User;

@Component
public class LoginMapper {
    public User toUser(LoginDto loginDto) {
        User user = new User();
        user.setUsername(loginDto.getUsername());
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());

        return user;
    }
}
