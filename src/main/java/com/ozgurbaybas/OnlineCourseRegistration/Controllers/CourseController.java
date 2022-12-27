package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseInstructorAssignRequest;
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
}