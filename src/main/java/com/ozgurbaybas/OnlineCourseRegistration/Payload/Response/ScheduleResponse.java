package com.ozgurbaybas.OnlineCourseRegistration.Payload.Response;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.EnumDay;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponse {

    private SemesterResponse semester;
    private Long id;
    private EnumDay day;
    private Long hour;
    private CourseResponse course;
    private UserResponse instructor;

    public ScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.semester = new SemesterResponse(schedule.getSemester());
        this.day = schedule.getDay();
        this.hour = schedule.getHour();
        this.course = new CourseResponse(schedule.getCourse());
        this.instructor = new UserResponse(schedule.getInstructor());
    }
}