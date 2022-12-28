package com.ozgurbaybas.OnlineCourseRegistration.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(	name = "courses",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Course extends BaseModel implements Comparable<Course>{

    public  Course() {
    }

    public Course(String name, Department department) {
        super();
        this.name = name;
        this.department = department;
    }

    @NotBlank
    @Size(max = 60)
    private String name;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private Boolean isApproved = false;

    @ManyToOne
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name="approved_or_rejected_by")
    private User approvedOrRejectedBy;

    @ManyToMany(mappedBy = "studentsCourses")
    private Set<User> students;

    @ManyToMany(mappedBy = "instructorsCourses")
    private Set<User> instructors;

    @ManyToMany(mappedBy= "courses")
    private Set<Semester> semesters;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Schedule> schedules;

    @Override
    public int compareTo(Course course) {
        return this.getId().compareTo(course.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) && Objects.equals(isApproved, course.isApproved) && Objects.equals(department, course.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isApproved, department);
    }
}