package com.diviya.todo.controller;
import com.diviya.todo.dto.TodoRequest;
import com.diviya.todo.dto.TodoResponse;
import com.diviya.todo.models.*;
import com.diviya.todo.service.TodoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    private String currentEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/create")
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        log.info("Received request to create todo with title : {}", request.getTitle());

        Todo createdTodo = todoService.createTodo(request, currentEmail());

        log.info("Todo created successfully with id : {}", createdTodo.getId());

        return new ResponseEntity<>(TodoResponse.fromEntity(createdTodo), HttpStatus.CREATED);
    }

    @ApiResponses(value={
            @ApiResponse(responseCode ="200", description = "Todo retrieved Successfully" ),
            @ApiResponse(responseCode = "404", description = "Todo was not found!")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        log.info("Received request to fetch todo with id: {}", id);
        try{
            Todo todo = todoService.getTodoById(id, currentEmail());

            log.info("Successfully fetched todo with id: {}", id);

            return new ResponseEntity<>(TodoResponse.fromEntity(todo), HttpStatus.OK);
        }
        catch(RuntimeException e){
            log.warn("Todo not found with id: {}. {}", id, e.getMessage());

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getTodos(){
        log.info("Received request to fetch all todos");

        List<TodoResponse> todos = todoService.getAllTodos(currentEmail())
                .stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());

        log.info("Fetched {} todos successfully", todos.size());

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodoById(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        log.info("Received request to update todo with id: {}", id);

        Todo updatedTodo = todoService.updateTodo(id, request, currentEmail());

        log.info("Todo updated successfully with id: {}", updatedTodo.getId());

        return new ResponseEntity<>(TodoResponse.fromEntity(updatedTodo), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TodoResponse>> getTodosPage(@RequestParam int page, @RequestParam int size){
        log.info("Fetching todos with page={} and size={}", page, size);

        Page<TodoResponse> todos = todoService.getTodosPages(currentEmail(), page, size)
                .map(TodoResponse::fromEntity);

        log.info("Fetched {} todos from page {}", todos.getNumberOfElements(), page);

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        log.info("Received request to delete todo with id: {}", id);

        todoService.deleteTodoById(id, currentEmail());

        log.info("Todo deleted successfully with id: {}", id);

        return ResponseEntity.noContent().build();
    }
}