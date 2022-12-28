package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {

    private Long id;
    private String name;
    private Long departmentId;
    private String departmentName;
    private boolean isApproved;
    private Long approvedOrRejectedById;
    private String approvedOrRejectedByName;
    private List<InstructorResponse> instructor;

    public CourseResponse(Course course, List<InstructorResponse> instructors) {

        this.id = course.getId();
        this.name = course.getName();

        if (course.getDepartment() != null) {
            this.departmentId = course.getDepartment().getId();
            this.departmentName = course.getDepartment().getName();
        }

        if (course.getInstructors() != null) {
            this.instructor = instructors;
        }

        if (course.getApprovedOrRejectedBy() != null) {
            this.approvedOrRejectedById = course.getApprovedOrRejectedBy().getId();
            this.approvedOrRejectedByName = course.getApprovedOrRejectedBy().getUsername();
        }

        this.isApproved = course.getIsApproved();

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

        this.isApproved = course.getIsApproved();

    }
    public CourseResponse (){

    }
}