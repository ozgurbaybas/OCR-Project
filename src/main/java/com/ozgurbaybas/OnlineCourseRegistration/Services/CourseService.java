package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseInstructorAssignRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseScheduleRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.OpenCourseInfoResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.ScheduleResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> listCourseRequests();

    CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest);

    CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

    List<CourseResponse> getOpenCourses();

    CourseResponse assignInstructorsToOpenCourses(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);

    ScheduleResponse assignScheduleToCourse(Long courseId, CourseScheduleRequest courseScheduleRequest);

    OpenCourseInfoResponse getOpenCourseInfo();
}
