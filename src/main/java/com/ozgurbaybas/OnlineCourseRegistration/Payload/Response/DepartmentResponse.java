package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Department;

public class DepartmentResponse {

    private Long id;
    private String name;
    private Long facultyId;
    private String facultyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        if (department.getFaculty() != null) {
            this.facultyId = department.getFaculty().getId();
            this.facultyName = department.getFaculty().getName();
        }
    }
}
