package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;

public class CourseResponse {

    private Long id;
    private String name;
    private Long departmentId;
    private String departmentName;
    private boolean isApproved;
    private Long approvedOrRejectedById;
    private String approvedOrRejectedByName;

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

    public Long getApprovedOrRejectedById() {
        return approvedOrRejectedById;
    }

    public void setApprovedOrRejectedById(Long approvedOrRejectedById) {
        this.approvedOrRejectedById = approvedOrRejectedById;
    }

    public String getApprovedOrRejectedByName() {
        return approvedOrRejectedByName;
    }

    public void setApprovedOrRejectedByName(String approvedOrRejectedByName) {
        this.approvedOrRejectedByName = approvedOrRejectedByName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public CourseResponse(Course course) {

        this.id = course.getId();
        this.name = course.getName();

        if (course.getDepartment() != null) {
            this.departmentId = course.getDepartment().getId();
            this.departmentName = course.getDepartment().getName();
        }

        if (course.getApprovedOrRejectedBy() != null) {
            this.approvedOrRejectedById = course.getApprovedOrRejectedBy().getId();
            this.approvedOrRejectedByName = course.getApprovedOrRejectedBy().getUsername();
        }

    }

}