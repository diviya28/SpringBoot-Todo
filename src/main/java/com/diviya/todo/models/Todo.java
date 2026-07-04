package com.diviya.todo.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
// @Data - import lambok.Data used to reduce repetative code for getters and setters method
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank(message = "Title cannot be blank")
    String title;
    @NotBlank(message = "Description cannot be blank")
    String description;
    @NotNull(message = "Completion status is required")
    Boolean isCompleted;

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
