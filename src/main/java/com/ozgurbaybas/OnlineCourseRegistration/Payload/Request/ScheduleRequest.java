package com.ozgurbaybas.OnlineCourseRegistration.Payload.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequest {
    private Long courseId;
    private Long instructorId;
    private String day;
    private Long hour;
}