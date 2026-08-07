package com.diviya.todo.dto;
import com.diviya.todo.models.Todo;

public class TodoResponse {
    private Long id;
    private String title;
    private Boolean isCompleted;

    public TodoResponse(Long id, String title, Boolean isCompleted){
        this.id=id;
        this.title=title;
        this.isCompleted=isCompleted;
    }

    public static TodoResponse fromEntity(Todo todo){
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.getIsCompleted());
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Boolean getIsCompleted() { return isCompleted; }
}
