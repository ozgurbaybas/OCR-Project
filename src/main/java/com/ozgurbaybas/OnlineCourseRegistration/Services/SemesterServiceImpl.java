package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Semester;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SelectedCourseRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterActivateRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterAddRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.SemesterResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.CourseRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SemesterServiceImpl implements SemesterService{
    final SemesterRepository semesterRepository;
    final CourseRepository courseRepository;

    public SemesterServiceImpl(SemesterRepository semesterRepository, CourseRepository courseRepository) {
        this.semesterRepository = semesterRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public SemesterResponse selectCoursesForSemester(Long semesterId, SelectedCourseRequest selectedCourseRequest) {
        Semester semester = semesterRepository.getById(semesterId);
        Set<Course> courses = courseRepository.getSelectedCourses(selectedCourseRequest.getSelectedCourseIds());
        semester.setCourses(courses);
        semesterRepository.save(semester);
        return new SemesterResponse(semester);
    }

    @Override
    public SemesterResponse addSemester(SemesterAddRequest semesterAddRequest) {
        Semester semester = semesterRepository.save(new Semester(semesterAddRequest.getName()));
        return new SemesterResponse(semester);
    }

    @Override
    public SemesterResponse activateSemester(SemesterActivateRequest semesterActivateRequest) {
        Semester semester = semesterRepository.getById(semesterActivateRequest.getId());
        semester.setActive(semesterActivateRequest.getActive());
        semester = semesterRepository.save(semester);
        return  new SemesterResponse(semester);
    }
}