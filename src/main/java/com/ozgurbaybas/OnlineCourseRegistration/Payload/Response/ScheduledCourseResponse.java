package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Schedule;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class ScheduledCourseResponse {

    private Long courseId;
    private String courseName;
    private Long instructorId;
    private String instructorName;
    private List<ScheduleResponse> scheduleResponses;

    public ScheduledCourseResponse(Course course, List<Schedule> schedules) {
        this.courseId = course.getId();
        this.courseName = course.getName();
        this.instructorId = schedules.get(0).getInstructor().getId();
        this.instructorName = schedules.get(0).getInstructor().getUsername();
        this.scheduleResponses = schedules.stream().map(ScheduleResponse::new).collect(Collectors.toList());


    }
}
