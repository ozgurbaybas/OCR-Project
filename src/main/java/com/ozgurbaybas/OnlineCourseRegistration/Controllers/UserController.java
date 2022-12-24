package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.DepartmentMemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/{memberId}/add_faculty")
    public ResponseEntity<?> addInstructorToFaculty(@PathVariable Long memberId, @Valid @RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(userService.addInstructorToFaculty(memberId, memberRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/{memberId}/add_department")
    public ResponseEntity<?> addInstructorToDepartment(@PathVariable Long memberId, @Valid @RequestBody DepartmentMemberRequest departmentMemberRequest) {
        return ResponseEntity.ok(userService.addInstructorToDepartment(memberId, departmentMemberRequest));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DEAN')")
    @PutMapping("/{memberId}/remove_faculty")
    public ResponseEntity<?> removeInstructorFromFaculty(@PathVariable Long memberId) {
        return ResponseEntity.ok(userService.removeInstructorFromFaculty(memberId));
    }



}
