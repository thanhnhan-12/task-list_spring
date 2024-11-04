package com.mvc.task_list.service.Auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.mvc.task_list.dto.LoginDto;
import com.mvc.task_list.dto.RegisterDto;
import com.mvc.task_list.jwt.JwtTokenProvider;
import com.mvc.task_list.mapper.RegisterMapper;
import com.mvc.task_list.model.Role;
import com.mvc.task_list.model.User;
import com.mvc.task_list.repository.RoleRepository;
import com.mvc.task_list.repository.UserRepository;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public User registUser(RegisterDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            System.out.println("Email is already taken: " + registerDto.getEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already taken");
        }

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            System.out.println("Password does not match");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirm Password do not match");
        }

        User user = registerMapper.toUser(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role for the user
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Default role not found"));
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }

    @Override
    public Map<String, Object> loginUser(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .or(() -> userRepository.findByEmail(loginDto.getEmail()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or email"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user);
        Date expirationDate = jwtTokenProvider.extractExpiration(token);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("expiration", expirationDate);
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("roles", user.getRoles().stream().map(role -> {
            Map<String, Object> roleData = new HashMap<>();
            roleData.put("id", role.getId());
            roleData.put("name", role.getName());
            return roleData;
        }).collect(Collectors.toList()));

        return response;
    }
}