package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Schedule;
import lombok.Builder;

import java.util.Set;

@Builder
public class OpenCourseInfoResponse  {

    private Long id;
    private String name;
    private Long instructorId;
    private String instructorName;
    private String semesterName;
    private Set<Schedule> schedules;

    public OpenCourseInfoResponse(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.instructorId = course.getInstructors();
    }
}