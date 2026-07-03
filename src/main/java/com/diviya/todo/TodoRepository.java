package com.project.todo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todo.models.Todo;

//CRUD - Create Read Update Delete
public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
