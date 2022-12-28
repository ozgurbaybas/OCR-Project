package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ScheduleResponse {

    private Long id;
    private String name;
    private Course course;
    private String day;
    private Set<Long> hour;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.course = schedule.getCourse();
        this.day = schedule.getDay();
        this.hour = schedule.getHour();
    }
}