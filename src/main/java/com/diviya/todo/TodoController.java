package com.diviya.todo;
import com.diviya.todo.models.*;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("/create")
    public ResponseEntity<Todo> createUser(@Valid @RequestBody Todo todo) {
        log.info("Received request to create todo with title : {}",todo.getTitle());

        Todo createTodo=todoService.createTodo(todo);

        log.info("Todo created successfully with id : {}",createTodo.getId());

        return new ResponseEntity<>(createTodo,HttpStatus.CREATED);
    }

    @ApiResponses(value={
        @ApiResponse(responseCode ="200", description = "Todo retrieved Successfully" ),
        @ApiResponse(responseCode = "404", description = "Todo was not found!")
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        log.info("Received request to fetch todo with id: {}", id);
        try{
            Todo status=todoService.getTodoById(id);

            log.info("Successfully fetched todo with id: {}", id);

            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        catch(RuntimeException e){
            log.warn("Todo not found with id: {}. {}", id, e.getMessage());

            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        log.info("Received request to fetch all todos");

        List<Todo> todos = todoService.getAllTodos();

        log.info("Fetched {} todos successfully", todos.size());

        return new ResponseEntity<>(todos,HttpStatus.OK);
    }
    
    @PutMapping()
    public ResponseEntity<Todo> updateTodoById(@Valid @RequestBody Todo todo) {
        log.info("Received request to update todo with id: {}", todo.getId());

        Todo updatedTodo = todoService.updateTodo(todo);

        log.info("Todo updated successfully with id: {}", updatedTodo.getId());

        return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Todo>> getTodos(@RequestParam int page,@RequestParam int size ){
        log.info("Fetching todos with page={} and size={}", page, size);

        Page<Todo> todos = todoService.getTodosPages(page, size);

        log.info("Fetched {} todos from page {}", todos.getNumberOfElements(), page);
        
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        log.info("Received request to delete todo with id: {}", id);

        todoService.deleteTodoBy(id);

        log.info("Todo deleted successfully with id: {}", id);
        
        return  ResponseEntity.noContent().build();
    }
}
