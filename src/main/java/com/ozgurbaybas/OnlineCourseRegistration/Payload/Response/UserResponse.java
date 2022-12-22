package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private final List<String> roles;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(role -> role.getName().getRoleName()).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}

