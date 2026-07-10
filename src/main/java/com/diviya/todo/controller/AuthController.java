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
        var userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User not found",HttpStatus.UNAUTHORIZED);
        }
        User user=userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
        }
        String token=jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");
        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
        } 
        userService.createUser(User.builder().email(email).password(password).build());
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }
}