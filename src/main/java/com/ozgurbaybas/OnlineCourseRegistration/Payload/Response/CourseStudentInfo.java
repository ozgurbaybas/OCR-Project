package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseStudentInfo {

    private Long courseId;
    private String courseName;
    private List<UserResponse> students;
    private Long studentCount;

    public CourseStudentInfo(Course course, List<UserResponse> studentList) {
        this.courseId = course.getId();
        this.courseName = course.getName();
        this.students = studentList;
        this.studentCount = (long) studentList.size();
    }
}