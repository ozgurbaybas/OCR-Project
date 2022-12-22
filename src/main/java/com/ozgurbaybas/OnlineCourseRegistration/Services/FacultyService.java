package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.FacultyResponse;

public interface FacultyService {
    FacultyResponse addFaculty(FacultyRequest facultyRequest);
}
