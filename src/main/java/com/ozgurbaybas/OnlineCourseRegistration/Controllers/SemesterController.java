package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SelectedCourseRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterActivateRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.SemesterAddRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.CourseService;
import com.ozgurbaybas.OnlineCourseRegistration.Services.SemesterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/semester")
public class SemesterController {

    final CourseService courseService;
    final SemesterService semesterService;

    public SemesterController(CourseService courseService, SemesterService semesterService) {
        this.courseService = courseService;
        this.semesterService = semesterService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addSemester(@Valid @RequestBody SemesterAddRequest semesterAddRequest) {
        return  ResponseEntity.ok(semesterService.addSemester(semesterAddRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/activate/{semesterId}")
    public ResponseEntity<?> activateSemester(@PathVariable Long semesterId, @Valid @RequestBody SemesterActivateRequest semesterActivateRequest) {
        return  ResponseEntity.ok(semesterService.activateSemester(semesterId, semesterActivateRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/select_course/{semesterId}")
    public ResponseEntity<?> selectCoursesForSemester (@PathVariable Long semesterId, @Valid @RequestBody SelectedCourseRequest selectedCourseRequest) {
        return ResponseEntity.ok(semesterService.selectCoursesForSemester(semesterId, selectedCourseRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @GetMapping("/")
    public ResponseEntity<?> listSemesters () {
        return ResponseEntity.ok(semesterService.listSemesters());
    }
}