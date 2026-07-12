package com.diviya.todo.controller;
import com.diviya.todo.models.*;
import com.diviya.todo.repository.UserRepository;
import com.diviya.todo.service.UserService;
import com.diviya.todo.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController{
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");
        log.info("Login request received for email: {}", email);

        var userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            log.warn("Login failed. User not found: {}", email);
            return new ResponseEntity<>("User not found",HttpStatus.UNAUTHORIZED);
        }
        User user=userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
             log.warn("Login failed. Invalid password for email: {}", email);
            return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
        }
        String token=jwtUtil.generateToken(email);
        log.info("User logged in successfully: {}", email);
        return ResponseEntity.ok(Map.of("token",token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");
        log.info("Registration request received for email: {}", email);

        if(userRepository.findByEmail(email).isPresent()){
            log.warn("Registration failed. Email already exists: {}", email);
            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
        } 
        userService.createUser(
                User.builder()
                    .email(email)
                    .password(password)
                    .build()
);

        log.info("User registered successfully: {}", email);
        return new ResponseEntity<>("User registered successfully",HttpStatus.CREATED);
    }
}