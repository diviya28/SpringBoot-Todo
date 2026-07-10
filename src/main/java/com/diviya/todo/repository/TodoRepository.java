package com.diviya.todo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diviya.todo.models.Todo;

//CRUD - Create Read Update Delete
public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
