package com.mvc.task_list.service.Role;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.task_list.model.Role;
import com.mvc.task_list.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    @Override
    public void initializeRoles() {
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");

        for (String roleName : roles) {
            if (roleRepository.findByName(roleName) == null) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }
}