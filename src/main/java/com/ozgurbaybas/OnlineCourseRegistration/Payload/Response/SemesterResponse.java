package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Semester;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SemesterResponse {
    private Long id;

    private String name;

    private Boolean isActive;

    private Set<Course> courses;

    public SemesterResponse(Semester semester) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.isActive = semester.getActive();
        this.courses = semester.getCourses();
    }
}