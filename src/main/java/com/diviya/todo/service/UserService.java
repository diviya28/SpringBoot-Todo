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

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }
}
