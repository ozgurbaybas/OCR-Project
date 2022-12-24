package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentMemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> listUsers();
    List<UserResponse> filterUsersWithFaculty(Long facultyId);
}
