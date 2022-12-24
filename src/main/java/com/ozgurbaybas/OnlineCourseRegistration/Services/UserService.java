package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> listUsers();
    UserResponse addInstructorToFaculty(Long memberId, MemberRequest memberRequest);
    UserResponse removeInstructorFromFaculty(Long memberId);
    UserResponse addInstructorToDepartment(Long memberId, MemberRequest memberRequest);
}
