package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.ScheduleRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PreAuthorize("hasRole('DEAN')")
    @PostMapping("/")
    public ResponseEntity<?> assignScheduleToCourse (@Valid @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(scheduleService.assignCourseToSchedule(scheduleRequest));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/users/open_courses")
    public ResponseEntity<?> getOpenCourses () {
        return ResponseEntity.ok(scheduleService.getOpenCourses());
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PutMapping("/users/register_course/{courseId}")
    public ResponseEntity<?> registerToCourse (@PathVariable Long courseId ) {
        return ResponseEntity.ok(scheduleService.registerToCourse(courseId));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/users/registered_courses")
    public ResponseEntity<?> getRegisteredCourses () {
        return ResponseEntity.ok(scheduleService.getRegisteredCourses());
    }

    @PreAuthorize("hasRole('STUDENT')")
    @DeleteMapping("/users/drop_course/{courseId}")
    public ResponseEntity<?> dropCourse (@PathVariable Long courseId) {
        return ResponseEntity.ok(scheduleService.dropCourse(courseId));
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @GetMapping("/users/instructors_courses")
    public ResponseEntity<?> getInstructorsCourses () {
        return ResponseEntity.ok(scheduleService.getInstructorsCourses());
    }
}