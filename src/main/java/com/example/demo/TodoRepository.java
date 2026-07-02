package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Todo;

//CRUD - Create Read Update Delete
public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
