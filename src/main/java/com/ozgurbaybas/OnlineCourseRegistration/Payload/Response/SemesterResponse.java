package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Semester;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SemesterResponse {
    private Long id;

    private String name;

    private Boolean isActive;

    private List<CourseResponse> courseList;

    public SemesterResponse(Semester semester, List<CourseResponse> courseResponseList) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.isActive = semester.getIsActive();
        this.courseList = courseResponseList;
    }

    public SemesterResponse(Semester semester) {
        this.id = semester.getId();
        this.name = semester.getName();
        this.isActive = semester.getIsActive();
    }
}