package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.Course;
import com.ozgurbaybas.OnlineCourseRegistration.Models.User;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.CourseResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.CourseRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    final CourseRepository courseRepository;
    final UserRepository userRepository;


    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CourseResponse> listCourseRequests() {
        List<Course> courseRequests = courseRepository.findAllByIsApproved(false);
        return courseRequests.stream().map(CourseResponse::new).collect(Collectors.toList());
    }

    @Override
    public CourseResponse approveOrRejectCourse(Long courseId, CourseApproveRequest courseApproveRequest) {
        User admin = userRepository.getReferenceById((courseApproveRequest.getApprovedOrRejectedById()));
        Course course = courseRepository.getById(courseId);
        course.setApprovedOrRejectedBy(admin);
        course.setApproved(courseApproveRequest.isApprove());
        courseRepository.save(course);
        return new CourseResponse(course);
    }

}