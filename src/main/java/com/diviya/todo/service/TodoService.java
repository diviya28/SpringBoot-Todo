package com.diviya.todo.service;
import com.diviya.todo.dto.TodoRequest;
import com.diviya.todo.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.diviya.todo.models.*;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.userRepository=userRepository;
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(TodoRequest request, String email){
        User owner = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setIsCompleted(request.getIsCompleted());
        todo.setOwner(owner);
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id,String email){
        return todoRepository.findTodoByIdAndOwnerEmail(id,email).orElseThrow(()->new RuntimeException("Todo not found"));
    }

    public List<Todo> getAllTodos(String email){
        return todoRepository.findAllByOwnerEmail(email);
    }

    public Page<Todo> getTodosPages(String email,int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return todoRepository.findAllByOwnerEmail(email,pageable);
    }

    public Todo updateTodo(Long id,TodoRequest request, String email){
        Todo existing = getTodoById(id, email);
        existing.setTitle(request.getTitle());
        existing.setIsCompleted(request.getIsCompleted());
        return todoRepository.save(existing);
    }

    public void deleteTodoById(Long id,String email){

        Todo todo=getTodoById(id,email);
        todoRepository.delete(todo);
    }
}
