package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Semester;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SelectedCourseRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterActivateRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterAddRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.SemesterResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.CourseRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.SemesterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<CourseResponse> courseResponseList = new ArrayList<>();
        for (Course course :
                courses) {
            CourseResponse newCourseRes = new CourseResponse(course);
            courseResponseList.add(newCourseRes);
        }
        semesterRepository.save(semester);
        return new SemesterResponse(semester, courseResponseList);
    }

    @Override
    public SemesterResponse addSemester(SemesterAddRequest semesterAddRequest) {
        Semester semester = semesterRepository.save(new Semester(semesterAddRequest.getName()));
        return new SemesterResponse(semester);
    }

    @Override
    public SemesterResponse activateSemester(Long semesterId, SemesterActivateRequest semesterActivateRequest) {
        List<Semester> allSemesters = semesterRepository.findAll();
        allSemesters.forEach(semester -> semester.setIsActive(false));
        allSemesters = semesterRepository.saveAll(allSemesters);
        Semester semester = semesterRepository.getById(semesterId);
        semester.setIsActive(semesterActivateRequest.getIsActive());
        semester = semesterRepository.save(semester);
        return new SemesterResponse(semester);
    }

    @Override
    public List<SemesterResponse> listSemesters() {
        List<Semester> semesterList = semesterRepository.findAll();
        List<SemesterResponse> semesterResponses = semesterList.stream().map(semester -> new SemesterResponse(semester)).collect(Collectors.toList());
        return semesterResponses;
    }
}