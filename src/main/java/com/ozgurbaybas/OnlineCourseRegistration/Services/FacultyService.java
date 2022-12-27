package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyDeanAssignmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.FacultyResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.MessageResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;

import java.util.List;

public interface FacultyService {
    MessageResponse addFaculty(FacultyRequest facultyRequest);
    List<FacultyResponse> listFaculties();
    FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest);
    FacultyResponse assignDeanToFaculty(Long facultyId, FacultyDeanAssignmentRequest facultyDeanAssignmentRequest);
    Void deleteFacultyById(Long facultyId);
    UserResponse addInstructorToFaculty(Long facultyId, MemberRequest memberRequest);
    UserResponse removeInstructorFromFaculty(MemberRequest memberRequest);
}
