package com.ozgurbaybas.OnlineCourseRegistration.Controllers;

import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyDeanAssignmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Services.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<?> addFaculty(@Valid @RequestBody FacultyRequest facultyRequest) {
        return ResponseEntity.ok(facultyService.addFaculty(facultyRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> listFaculties() {
        return ResponseEntity.ok(facultyService.listFaculties());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{facultyId}")
    public ResponseEntity<?> updateFaculty(@PathVariable Long facultyId, @Valid @RequestBody FacultyRequest facultyRequest) {
        return ResponseEntity.ok(facultyService.updateFaculty(facultyId, facultyRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{facultyId}/assignDean")
    public ResponseEntity<?> assignDean(@PathVariable Long facultyId,
                                        @Valid @RequestBody FacultyDeanAssignmentRequest facultyDeanAssignmentRequest) {
        return ResponseEntity.ok(facultyService.assignDeanToFaculty(facultyId, facultyDeanAssignmentRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{facultyId}")
    public ResponseEntity<?> deleteFacultyById(@PathVariable Long facultyId) {
        return ResponseEntity.ok(facultyService.deleteFacultyById(facultyId));
    }

}
