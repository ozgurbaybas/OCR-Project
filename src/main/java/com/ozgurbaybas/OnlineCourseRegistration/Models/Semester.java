package com.ozgurbaybas.OnlineCourseRegistration.Models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "semesters",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @Column(name="is_active")
    private Boolean isActive = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "semester_courses",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    public Semester(String name) {
        this.name = name;
    }

    public Semester() {

    }
}