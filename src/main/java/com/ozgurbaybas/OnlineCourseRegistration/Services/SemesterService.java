package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SelectedCourseRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterActivateRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterAddRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.SemesterResponse;

import java.util.List;

public interface SemesterService {

    SemesterResponse selectCoursesForSemester(Long semesterId, SelectedCourseRequest selectedCourseRequest);
    SemesterResponse addSemester(SemesterAddRequest semesterAddRequest);
    SemesterResponse activateSemester(Long semesterId, SemesterActivateRequest semesterActivateRequest);
    List<SemesterResponse> listSemesters();

}