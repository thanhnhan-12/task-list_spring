package com.mvc.task_list.mapper;

import org.springframework.stereotype.Component;

import com.mvc.task_list.dto.RegisterDto;
import com.mvc.task_list.model.User;

@Component
public class RegisterMapper {
    public User toUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());

        return user;
    }
}
