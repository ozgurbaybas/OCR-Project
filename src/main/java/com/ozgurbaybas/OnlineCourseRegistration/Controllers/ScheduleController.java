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
    @GetMapping("/users/{studentId}/open_courses")
    public ResponseEntity<?> getOpenCourses (@PathVariable Long studentId) {
        return ResponseEntity.ok(scheduleService.getOpenCourses(studentId));
    }

}
