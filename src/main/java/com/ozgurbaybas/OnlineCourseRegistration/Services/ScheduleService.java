package com.ozgurbaybas.OnlineCourseRegistration.Services;


import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.ScheduleRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.ScheduleResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.ScheduledCourseResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse assignCourseToSchedule(ScheduleRequest scheduleRequest);
    List<ScheduledCourseResponse> getOpenCourses(Long studentId);
}