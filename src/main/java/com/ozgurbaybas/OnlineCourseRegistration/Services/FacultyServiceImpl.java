package com.ozgurbaybas.OnlineCourseRegistration.Services;

import com.ozgurbaybas.OnlineCourseRegistration.Models.EnumRole;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Faculty;
import com.ozgurbaybas.OnlineCourseRegistration.Models.Role;
import com.ozgurbaybas.OnlineCourseRegistration.Models.User;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyDeanAssignmentRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.FacultyRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Request.MemberRequest;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.FacultyResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Payload.Response.UserResponse;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.FacultyRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.RoleRepository;
import com.ozgurbaybas.OnlineCourseRegistration.Repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService{

    final FacultyRepository facultyRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository, UserRepository userRepository, RoleRepository roleRepository)
    {
        this.facultyRepository = facultyRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FacultyResponse addFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.save(new Faculty(facultyRequest.getName()));
        return new FacultyResponse(faculty);
    }

    @Override
    public List<FacultyResponse> listFaculties() {
        List<Faculty> faculties = facultyRepository.findAllByIsActive(true);
        return faculties.stream().map(FacultyResponse::new).collect(Collectors.toList());
    }

    @Override
    public FacultyResponse updateFaculty(Long facultyId, FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.getReferenceById(facultyId);
        faculty.setName(facultyRequest.getName());
        facultyRepository.save(faculty);
        return new FacultyResponse(faculty);
    }

    @Override
    public FacultyResponse assignDeanToFaculty(Long facultyId, FacultyDeanAssignmentRequest facultyDeanAssignmentRequest) {
        Faculty faculty = facultyRepository.getReferenceById(facultyId);
        User deanUser = userRepository.getReferenceById(facultyDeanAssignmentRequest.getDeanId());
        faculty.setDean(deanUser);
        faculty = facultyRepository.save(faculty);

        Role deanRole = roleRepository.findByName(EnumRole.ROLE_DEAN).orElseThrow(() -> new RuntimeException("Role is not found."));
        deanUser.getRoles().add(deanRole);
        userRepository.save(deanUser);

        return new FacultyResponse(faculty);
    }

    @Override
    public Void deleteFacultyById(Long facultyId) {
        Faculty faculty = facultyRepository.getReferenceById(facultyId);
        facultyRepository.deleteById(facultyId);
        return null;
    }

    @Override
    public UserResponse addInstructorToFaculty(Long facultyId, MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberRequest.getMemberId());
        Faculty faculty = facultyRepository.getById(facultyId);
        instructor.setFaculty(faculty);
        instructor = userRepository.save(instructor);

        Role instructorRole = roleRepository.findByName(EnumRole.ROLE_INSTRUCTOR).orElseThrow(() -> new RuntimeException("Role is not found."));
        instructor.getRoles().add(instructorRole);
        userRepository.save(instructor);

        return new UserResponse(instructor);
    }

    @Override
    public UserResponse removeInstructorFromFaculty(MemberRequest memberRequest) {

        User instructor = userRepository.getById(memberRequest.getMemberId());
        instructor.setFaculty(null);
        instructor = userRepository.save(instructor);
        return  new UserResponse(instructor);
    }
}
