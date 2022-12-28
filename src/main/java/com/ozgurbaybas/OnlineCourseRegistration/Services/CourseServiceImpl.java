package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Schedule;
import com.ozgurbaybas.OnlineCourseRegistration.Models.User;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseInstructorAssignRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseScheduleRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.ScheduleResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.CourseRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.ScheduleRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.SemesterRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    final CourseRepository courseRepository;
    final UserRepository userRepository;
    final SemesterRepository semesterRepository;
    final ScheduleRepository scheduleRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, SemesterRepository semesterRepository, ScheduleRepository scheduleRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.semesterRepository = semesterRepository;
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public List<CourseResponse> listCourseRequests() {
        List<Course> courseRequests = courseRepository.findAllByIsApproved(false);
        return courseRequests.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest) {
        User admin = userRepository.getById(courseApproveRequest.getApprovedOrRejectedById());
        Course course = courseRepository.getById(courseId);
        course.setApprovedOrRejectedBy(admin);
        course.setApproved(courseApproveRequest.isApprove());
        courseRepository.save(course);
        return new CourseResponse(course);
    }

    @Override
    public CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest) {
        User instructor = userRepository.getById(courseInstructorAssignRequest.getInstructorId());
        Course course = courseRepository.getById(courseId);
        Set<User> instructorList = course.getInstructors();
        instructorList.add(instructor);
        courseRepository.save(course);
        return new CourseResponse(course);
    }

    @Override
    public List<CourseResponse> getOpenCourses() {
        List<Course> openCourses = courseRepository.findAllBySemester_IsActive(true);
        return openCourses.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse assignInstructorsToOpenCourses(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest) {
        User instructor = userRepository.getById(courseInstructorAssignRequest.getInstructorId());
        Course course = courseRepository.getById(courseId);
        Set<User> instructorList = course.getInstructors();
        instructorList.add(instructor);
        courseRepository.save(course);
        return new CourseResponse(course);
    }

    @Override
    public ScheduleResponse assignScheduleToCourse(Long courseId, CourseScheduleRequest courseScheduleRequest) {
        Course course = courseRepository.getById(courseId);
        String day = courseScheduleRequest.getDay();
        Set<Long> hour = courseScheduleRequest.getHour();
        Schedule schedule = new Schedule(course, day, hour);
        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule);
    }

}
