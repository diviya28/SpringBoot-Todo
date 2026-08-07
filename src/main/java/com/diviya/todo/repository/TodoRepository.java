package com.diviya.todo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diviya.todo.models.Todo;

import java.util.List;
import java.util.Optional;

//CRUD - Create Read Update Delete
public interface TodoRepository extends JpaRepository<Todo, Long>{
    Optional<Todo> findTodoByIdAndOwnerEmail(Long id, String email);
    List<Todo> findAllByOwnerEmail(String email);
    Page<Todo> findAllByOwnerEmail(String email, Pageable pageable);
}
