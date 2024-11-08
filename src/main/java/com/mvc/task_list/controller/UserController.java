package com.mvc.task_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.task_list.dto.UserDto;
import com.mvc.task_list.service.User.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-profile")
    public UserDto getMethodName() {
        return userService.getUserProfile();
    }

}
