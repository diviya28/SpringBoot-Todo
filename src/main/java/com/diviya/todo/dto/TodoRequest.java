package com.diviya.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Completion status is required")
    private Boolean isCompleted;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}