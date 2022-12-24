package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.DepartmentResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse addDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> listDepartments();
    List<DepartmentResponse> listDepartmentsByFaculty(Long facultyId);
    DepartmentResponse updateDepartment(Long departmentId, DepartmentRequest departmentRequest);
    Void deleteDepartmentById(Long departmentId);
    UserResponse addInstructorToDepartment(Long departmentId, MemberRequest memberRequest);
}