package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/faculty={facultyId}")
    public ResponseEntity<?> filterUsersWithFaculty(@PathVariable Long facultyId) {
        return ResponseEntity.ok(userService.filterUsersWithFaculty(facultyId));
    }
}