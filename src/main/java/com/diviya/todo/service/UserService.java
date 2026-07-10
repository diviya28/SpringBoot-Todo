package com.diviya.todo.service;
import org.springframework.stereotype.Service;
import com.diviya.todo.repository.*;
import com.diviya.todo.models.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createTodo(User user){
        return userRepository.save(user);
    }

    public User getTodoById(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }
}
