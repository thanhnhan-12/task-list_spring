package com.mvc.task_list.mapper;

import com.mvc.task_list.dto.RoleDto;
import com.mvc.task_list.model.Role;

public class RoleMapper {
    public RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
