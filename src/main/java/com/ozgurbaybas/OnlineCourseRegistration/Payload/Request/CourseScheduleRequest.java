package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseScheduleRequest {
    private String day;
    private Long hour;
}