package com.ozgurbaybas.OnlineCourseRegistration.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(	name = "departments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Department extends BaseModel {

    @NotBlank
    @Size(max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="faculty_id", nullable = false)
    private Faculty faculty;

    public Department() {
    }

    public Department(String name, Faculty faculty) {
        super();
        this.name = name;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(User dean) {
        this.faculty = faculty;
    }

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<User> instructor;
}