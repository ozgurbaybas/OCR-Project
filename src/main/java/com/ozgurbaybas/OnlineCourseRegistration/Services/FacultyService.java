package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyDeanAssignmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.FacultyResponse;

import java.util.List;

public interface FacultyService {
    FacultyResponse addFaculty(FacultyRequest facultyRequest);
    List<FacultyResponse> listFaculties();
    FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest);
    FacultyResponse assignDeanToFaculty(Long facultyId, FacultyDeanAssignmentRequest facultyDeanAssignmentRequest);
    Void deleteFacultyById(Long facultyId);
}
