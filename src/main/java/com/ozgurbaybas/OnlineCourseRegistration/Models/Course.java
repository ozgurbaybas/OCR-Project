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

    @ManyToMany(mappedBy= "semester_courses")
    private Set<Semester> semester;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Schedule> schedules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public User getApprovedOrRejectedBy() {
        return approvedOrRejectedBy;
    }

    public void setApprovedOrRejectedBy(User approvedOrRejectedBy) {
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

    public Set<Semester> getSemester() {
        return semester;
    }

    public void setSemester(Set<Semester> semester) {
        this.semester = semester;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }
}
