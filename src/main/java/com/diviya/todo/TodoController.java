package com.project.todo;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;

import com.project.todo.models.*;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Todo> createUser(@Valid @RequestBody Todo todo) {
        Todo createTodo=todoService.createTodo(todo);
        return new ResponseEntity<>(createTodo,HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        try{
            Todo status=todoService.getTodoById(id);
            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        catch(RuntimeException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        return new ResponseEntity<>(todoService.getAllTodos(),HttpStatus.OK);
    }
    
    @PutMapping()
    public ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(todo),HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Todo>> getTodos(@RequestParam int page,@RequestParam int size ){
        return new ResponseEntity<>(todoService.getTodosPages(page, size),HttpStatus.OK);
    }
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoBy(id);
    }
}
