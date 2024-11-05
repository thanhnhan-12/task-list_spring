package com.mvc.task_list.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.mvc.task_list.dto.RoleDto;
import com.mvc.task_list.dto.UserDto;
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
        userDto.setEmailVerified(user.isEmailVerified());
        userDto.setCreatedDate(user.getCreatedDate());

        Set<RoleDto> roles = user.getRoles().stream()
                .map(roleMapper::toRoleDto)
                .collect(Collectors.toSet());
        userDto.setRoles(roles);

        return userDto;
    }
}
