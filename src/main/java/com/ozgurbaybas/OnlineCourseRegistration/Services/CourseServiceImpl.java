package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.User;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseInstructorAssignRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseStudentInfo;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.InstructorResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.CourseRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.ScheduleRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.SemesterRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Security.Services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long adminId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        User admin = userRepository.getById(adminId);
        Course course = courseRepository.getById(courseId);
        course.setApprovedOrRejectedBy(admin);
        course.setIsApproved(courseApproveRequest.isApprove());
        courseRepository.save(course);
        return new CourseResponse(course);
    }

    @Override
    public CourseResponse assignInstructorToCourse(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest) {
        User instructor = userRepository.findById(courseInstructorAssignRequest.getInstructorId()).get();
        Course course = courseRepository.getById(courseId);
        Set<User> instructorList = course.getInstructors();
        instructorList.add(instructor);
        InstructorResponse instructorResponse;
        List<InstructorResponse> instructorResponseList = instructorList.stream().map(inst -> new InstructorResponse(inst.getId(), inst.getUsername())).collect(Collectors.toList());
        courseRepository.save(course);
        return new CourseResponse(course, instructorResponseList);
    }

    @Override
    public List<CourseResponse> getOpenCourses() {
        List<Course> openCourses = courseRepository.findAllBySemesters_IsActiveOrderById(true);
        return openCourses.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse assignInstructorsToOpenCourses(Long courseId, CourseInstructorAssignRequest courseInstructorAssignRequest) {
        User instructor = userRepository.findById(courseInstructorAssignRequest.getInstructorId()).get();
        Course course = courseRepository.getById(courseId);
        Set<User> instructorList = course.getInstructors();
        instructorList.add(instructor);
        InstructorResponse instructorResponse;
        List<InstructorResponse> instructorResponseList = new ArrayList<>();
        for (User inst : instructorList) {
            instructorResponse = new InstructorResponse(inst.getId(), inst.getUsername());
            instructorResponseList.add(instructorResponse);
        }
        //        List<InstructorResponse> instructorResponseList = instructorList.stream().map(inst -> new InstructorResponse(inst.getId(), inst.getUsername())).collect(Collectors.toList());
        courseRepository.save(course);
        return new CourseResponse(course, instructorResponseList);
    }

    @Override
    public CourseStudentInfo getCourseStudentInfo(Long courseId) {
        Course course = courseRepository.getById(courseId);
        List<UserResponse> studentList = course.getStudents().stream().map(UserResponse::new).collect(Collectors.toList());
        return new CourseStudentInfo(course,studentList);
    }

    @Override
    public CourseStudentInfo getCourseStudentInfoForInstructor(Long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
        User instructor = userRepository.getById(userId);

        Course course = courseRepository.getById(courseId);
        if (instructor.getInstructorsCourses().contains(course)) {
            List<UserResponse> studentList = course.getStudents().stream().map(UserResponse::new).collect(Collectors.toList());
            return new CourseStudentInfo(course,studentList);
        }
        else throw new RuntimeException(course.getName()+" isimli ders bu eğitmene ait değildir.");
    }
}