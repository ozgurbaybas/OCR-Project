package com.ozgurbaybas.OnlineCourseRegistration.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(	name = "faculties",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Faculty extends BaseModel {

    @NotBlank
    @Size(max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="dean_id")
    private User dean;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Department> departments;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<User> instructor;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Course> courses;

    public Faculty() {
    }

    public Faculty(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getDean() {
        return dean;
    }

    public void setDean(User dean) {
        this.dean = dean;
    }
}
