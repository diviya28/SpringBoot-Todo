package com.diviya.todo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diviya.todo.models.User;

//CRUD - Create Read Update Delete
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String mail);
}
