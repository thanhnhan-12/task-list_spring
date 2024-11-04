package com.mvc.task_list.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.mvc.task_list.dto.RoleDto;
import com.mvc.task_list.dto.UserDto;
import com.mvc.task_list.model.Role;
import com.mvc.task_list.model.User;

public class UserMapper {
    private final RoleMapper roleMapper = new RoleMapper();

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEnabled(user.getEnabled());
        userDto.setCreatedDate(user.getCreatedDate());

        Set<RoleDto> roles = user.getRoles().stream()
                .map(roleMapper::toRoleDto)
                .collect(Collectors.toSet());
        userDto.setRoles(roles);

        return userDto;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEnabled(userDto.isEnabled());

        Set<Role> roles = userDto.getRoles().stream()
                .map(roleMapper::toRole)
                .collect(Collectors.toSet());
        user.setRoles(roles);

        return user;
    }
}
