package com.diviya.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Email
    @Column(unique=true,nullable=false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String password;

    public User(){

    }
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
