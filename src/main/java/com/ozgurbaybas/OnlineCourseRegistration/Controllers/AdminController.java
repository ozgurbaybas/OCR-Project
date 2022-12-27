package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseApproveRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    final CourseService courseService;

    public AdminController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/course_request")
    public ResponseEntity<?> listCourseRequests() {
        return ResponseEntity.ok(courseService.listCourseRequests());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/course_request/{courseId}")
    public  ResponseEntity<?> approveOrRejectCourse(@PathVariable Long courseId, @Valid @RequestBody CourseApproveRequest courseApproveRequest) {
        return  ResponseEntity.ok(courseService.approveOrRejectCourse(courseId,courseApproveRequest));
    }

}
