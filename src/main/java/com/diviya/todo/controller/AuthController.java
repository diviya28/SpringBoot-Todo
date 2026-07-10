package com.diviya.todo.controller;
import com.diviya.todo.models.*;
import com.diviya.todo.service.UserService;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{
    private final UserService userService;

    AuthController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody Map<String,String> body){
        return "Login API";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String,String> body){
        return "Register API";

    }
}