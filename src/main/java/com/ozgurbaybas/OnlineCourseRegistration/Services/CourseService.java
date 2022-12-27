package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseInstructorAssignRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> listCourseRequests();

    CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest);

    CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest);
}
