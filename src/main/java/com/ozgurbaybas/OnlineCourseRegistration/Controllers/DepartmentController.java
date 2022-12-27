package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.CourseAddRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PostMapping("/")
    public ResponseEntity<?> addDepartment (@Valid @RequestBody DepartmentRequest departmentRequest) {
        return  ResponseEntity.ok(departmentService.addDepartment(departmentRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @GetMapping("/")
    public ResponseEntity<?> listDepartments () {
        return  ResponseEntity.ok(departmentService.listDepartments());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @GetMapping("/faculty={facultyId}")
    public ResponseEntity<?> listDepartmentsByFaculty (@PathVariable Long facultyId) {
        return  ResponseEntity.ok(departmentService.listDepartmentsByFaculty(facultyId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/{departmentId}")
    public ResponseEntity<?> updateDepartment
            (@PathVariable Long departmentId, @Valid @RequestBody DepartmentRequest departmentRequest) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId,departmentRequest ));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.deleteDepartmentById(departmentId));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/{departmentId}/add_instructor")
    public ResponseEntity<?> addInstructorToDepartment(@PathVariable Long departmentId, @Valid @RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(departmentService.addInstructorToDepartment(departmentId, memberRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN') or hasRole('INSTRUCTOR')")
    @PostMapping("/{departmentId}/add_course")
    public ResponseEntity<?> addCourseRequest(@PathVariable Long departmentId, @Valid @RequestBody CourseAddRequest courseAddRequest) {
        return ResponseEntity.ok(departmentService.addCourseRequest(departmentId, courseAddRequest));
    }
}