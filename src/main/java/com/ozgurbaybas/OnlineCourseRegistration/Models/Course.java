package com.ozgurbaybas.OnlineCourseRegistration.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(	name = "courses",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Course extends BaseModel {

    @NotBlank
    @Size(max = 60)
    private String name;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private Boolean isApproved = false;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    private Long approvedOrRejectedBy;

    @ManyToMany(mappedBy = "studentsCourses")
    private Set<User> students;

    @ManyToMany(mappedBy = "instructorsCourses")
    private Set<User> instructors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Long getApprovedOrRejectedBy() {
        return approvedOrRejectedBy;
    }

    public void setApprovedOrRejectedBy(Long approvedOrRejectedBy) {
        this.approvedOrRejectedBy = approvedOrRejectedBy;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public Set<User> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<User> instructors) {
        this.instructors = instructors;
    }
}
