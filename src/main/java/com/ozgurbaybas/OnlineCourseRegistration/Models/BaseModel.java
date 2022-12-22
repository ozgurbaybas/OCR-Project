package com.ozgurbaybas.OnlineCourseRegistration.Models;

import javax.persistence.*;
import java.time.LocalDateTime;


@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isActive = true;

    @Basic
    private LocalDateTime createdAt = LocalDateTime.now();

    @Version
    private long version;

    protected BaseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
