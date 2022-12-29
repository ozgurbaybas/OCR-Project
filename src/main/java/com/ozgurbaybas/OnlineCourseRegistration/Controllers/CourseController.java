package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseInstructorAssignRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseScheduleRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/course")
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/assign/{courseId}")
    public ResponseEntity<?> assignInstructorToCourse(@PathVariable Long courseId, @Valid @RequestBody CourseInstructorAssignRequest courseInstructorAssignRequest) {
        return ResponseEntity.ok(courseService.assignInstructorToCourse(courseId,courseInstructorAssignRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/request")
    public ResponseEntity<?> listCourseRequests() {
        return ResponseEntity.ok(courseService.listCourseRequests());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/request/{courseId}")
    public  ResponseEntity<?> approveOrRejectCourse(@PathVariable Long courseId, @Valid @RequestBody CourseApproveRequest courseApproveRequest) {
        return  ResponseEntity.ok(courseService.approveOrRejectCourse(courseId,courseApproveRequest));
    }

    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/open_courses")
    public  ResponseEntity<?> listOpenCourses () {
        return ResponseEntity.ok(courseService.getOpenCourses());
    }

    @PreAuthorize("hasRole('DEAN')")
    @PutMapping("/open_courses/assign/{courseId}")
    public ResponseEntity<?> assignInstructorsToOpenCourses (@PathVariable Long courseId, @Valid @RequestBody CourseInstructorAssignRequest courseInstructorAssignRequest) {
        return ResponseEntity.ok(courseService.assignInstructorsToOpenCourses(courseId, courseInstructorAssignRequest));
    }

    @PreAuthorize("hasRole('DEAN')")
    @GetMapping("/open_courses/{courseId}/info")
    public ResponseEntity<?> getCourseStudentInfo (@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseStudentInfo(courseId));
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/open_courses/{courseId}/instructor_info")
    public ResponseEntity<?> getCourseStudentInfoForInstructor (@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseStudentInfoForInstructor(courseId));
    }
}